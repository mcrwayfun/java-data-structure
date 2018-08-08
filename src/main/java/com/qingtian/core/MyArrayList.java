package com.qingtian.core;

import java.util.Arrays;

/**
 * @author mcrwayfun
 * @version 1.0
 * @description
 * @date Created in 2018/8/8
 */
public class MyArrayList {

    /**
     * 默认初始化容量
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 被用于创建空实例的共享空数组
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};

    /**
     * 被用于创建默认大小的空实例的共享数组实例。
     * 其与EMPTY_ELEMENTDATA的区别是：当我们向数组中添加第一个元素时，便知道数组该扩充多少。
     */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     * Object[]类型的数组，保存了添加到ArrayList中的元素。ArrayList的容量是该Object[]类型数组的长度
     * 当第一个元素被添加时，任何空ArrayList中的elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA将会被
     * 扩充到DEFAULT_CAPACITY（默认容量）。
     */
    transient Object[] elementData; // non-private to simplify nested class access

    /**
     * 表中拥有的元素个数
     */
    private int size;

    /**
     * 结构发生变化的次数
     */
    protected transient int modCount = 0;

    /**
     * 用初始值作为数组长度来初始化elementData
     * 当initialCapacity == 0 时用空数组初始化elementData
     *
     * @param  initialCapacity
     * @throws IllegalArgumentException initialCapacity为负数时抛出异常
     *
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }

    /**
     * 默认使用空数组DEFAULTCAPACITY_EMPTY_ELEMENTDATA初始化elementData
     */
    public MyArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    /**
     * 将数组长度缩减为当前有效长度
     * 涉及数组的长度，时间复杂度为O(size),所以需要耗费性能
     */
    public void trimToSize() {
        // 结构改变，+1
        modCount++;
        // 数组长度大于有效长度
        if (size < elementData.length) {
            // size == 0则赋予一个空数组
            // 否则执行数组复制操作，数组长度缩减为当前有效长度
            elementData = (size == 0)
                    ? EMPTY_ELEMENTDATA
                    : Arrays.copyOf(elementData, size);
        }
    }

    /**
     * Increases the capacity of this <tt>ArrayList</tt> instance, if
     * necessary, to ensure that it can hold at least the number of elements
     * specified by the minimum capacity argument.
     *
     * @param   minCapacity   the desired minimum capacity
     */
    public void ensureCapacity(int minCapacity) {
        int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
                // any size if not default element table
                ? 0
                // larger than default for default empty table. It's already
                // supposed to be at default size.
                : DEFAULT_CAPACITY;

        if (minCapacity > minExpand) {
            ensureExplicitCapacity(minCapacity);
        }
    }

    private void ensureCapacityInternal(int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }

        ensureExplicitCapacity(minCapacity);
    }

    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;

        // overflow-conscious code
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }

    /**
     * The maximum size of array to allocate.
     * Some VMs reserve some header words in an array.
     * Attempts to allocate larger arrays may result in
     * OutOfMemoryError: Requested array size exceeds VM limit
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * Increases the capacity to ensure that it can hold at least the
     * number of elements specified by the minimum capacity argument.
     *
     * @param minCapacity the desired minimum capacity
     */
    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }
}
