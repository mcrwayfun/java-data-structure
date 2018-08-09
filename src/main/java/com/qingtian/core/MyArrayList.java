package com.qingtian.core;

import java.util.*;

/**
 * @author mcrwayfun
 * @version 1.0
 * @description
 * @date Created in 2018/8/8
 */
public class MyArrayList<E> {

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
     * @param initialCapacity
     * @throws IllegalArgumentException initialCapacity为负数时抛出异常
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " +
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
     * 涉及数组的移动，所以需要耗费性能，时间复杂度为O(n)
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
     * 让用户能够手动设置ArrayList容量
     * 涉及数组的移动，所以需要耗费性能，时间复杂度为O(n)
     *
     * @param minCapacity 期望的最小容量（不是增长的容量）
     */
    public void ensureCapacity(int minCapacity) {
        int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
                // any size if not default element table
                ? 0
                // larger than default for default empty table. It's already
                // supposed to be at default size.
                : DEFAULT_CAPACITY;

        // 期望的容量大于当前最小的容量，才需要扩容
        if (minCapacity > minExpand) {
            ensureExplicitCapacity(minCapacity);
        }
    }

    private void ensureCapacityInternal(int minCapacity) {
        // 没有经过add操作扩容,elementData是一个空数组
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            // 获取扩容的期望值
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }

        ensureExplicitCapacity(minCapacity);
    }

    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;

        // overflow-conscious code
        // 期望扩容值大于当前elementData数组长度才需要执行扩容
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }

    /**
     * 要分配的数组大小
     * 有些VM会在数组中保留标题字
     * 尝试给数组分配更大的长度可能会导致OutOfMemoryError
     * 请求的数组大小超过了限制
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * 增加ArrayList的容量，确保它至少能够容纳期望容量的元素
     *
     * @param minCapacity 期望容量
     */
    private void grow(int minCapacity) {
        // overflow-conscious code
        // 原先的数组长度
        int oldCapacity = elementData.length;
        // oldCapacity >> 1，左移1位，相当于除2
        // newCapacity = oldCapacity + 0.5oldCapacity = 1.5oldCapacity
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        // newCapacity小于期望容量minCapacity，则按照期望容量来扩容
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        // newCapacity超过数组长度最大值
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    /**
     * 超出数组最大容量
     *
     * @param minCapacity
     * @return
     */
    private static int hugeCapacity(int minCapacity) {
        // 溢出情况下存在OOM
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    /**
     * 返回列中的元素个数
     *
     * @return 返回列中的元素个数
     */
    public int size() {
        return size;
    }

    /**
     * 判断列表是否为空
     *
     * @return 列表为空则返回true
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判断当前列表是否至少含有一个元素o
     * 时间复杂度为O(n)
     *
     * @param o 要测试的元素o
     * @return 若在列表中则返回true
     */
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * 返回o元素在列表中（正序）第一次出现的位置的下标，若不存在则返回-1
     * 时间复杂度为O(n)
     */
    public int indexOf(Object o) {
        // o为null值
        if (o == null) {
            // 遍历数组找到第一个匹配的下标
            for (int i = 0; i < size; i++)
                if (elementData[i] == null)
                    return i;
        } else {
            // o为数值
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        // 不存在则返回-1
        return -1;
    }

    /**
     * 返回o元素在列表中（反序）第一次出现的位置的下标，若不存在则返回-1
     * 时间复杂度为O(n)
     */
    public int lastIndexOf(Object o) {
        // o为null值
        if (o == null) {
            // 遍历数组找到第一个匹配的下标
            for (int i = size - 1; i >= 0; i--)
                if (elementData[i] == null)
                    return i;
        } else {
            // o为数值
            for (int i = size - 1; i >= 0; i--)
                if (o.equals(elementData[i]))
                    return i;
        }
        // 不存在则返回-1
        return -1;
    }

    /**
     * 返回一个浅复制的实例，ArrayList中的元素并不会被复制
     *
     * @return
     */
    public Object clone() {
        try {
            MyArrayList<?> v = (MyArrayList<?>) super.clone();
            v.elementData = Arrays.copyOf(elementData, size);
            v.modCount = 0;
            return v;
        } catch (CloneNotSupportedException e) {
            // this shouldn't happen, since we are Cloneable
            throw new InternalError(e);
        }
    }

    // O(1)操作
    @SuppressWarnings("unchecked")
    E elementData(int index) {
        return (E) elementData[index];
    }

    /**
     * 返回列中中指定位置的元素
     *
     * @param index 指定位置
     * @return 列中中指定位置的元素
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E get(int index) {
        rangeCheck(index);

        return elementData(index);
    }

    /**
     * 用element来替换列表中指定位置的元素
     * 时间复杂度为O(1)
     *
     * @param index   被替换元素的下标
     * @param element 替换的元素
     * @return 被替换的元素
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E set(int index, E element) {
        // 边界值检查
        rangeCheck(index);

        E oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }

    /**
     * 在列表的末尾添加指定的元素
     * 涉及扩容时，会影响性能，时间复杂度为O(n)
     *
     * @param
     * @return
     */
    public boolean add(E e) {
        // 第一次add会用DEFAULT_CAPACITY初始化数组
        // 之后每次add都会让有效长度size+1，但是不一定扩容
        // 只有在size+1 > elementData.length 的情况下扩容
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        elementData[size++] = e;
        return true;
    }

    /**
     * 在列表的指定位置index插入元素，index后的元素向右移动
     * 涉及扩容时，会影响性能，时间复杂度为O(n)
     *
     * @param index
     * @param element
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        // 扩容
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        System.arraycopy(elementData, index, elementData, index + 1,
                size - index);
        elementData[index] = element;
        size++;
    }

    /**
     * 移除指定位置的element，剩余元素向左移动
     * 时间复杂度为O(n)
     *
     * @param index
     * @return
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E remove(int index) {
        rangeCheck(index);

        modCount++;
        E oldValue = elementData(index);

        // numMoved = 0时，移除元素为末尾元素
        int numMoved = size - index - 1;
        // 移除末尾元素，数组就不用移动
        if (numMoved > 0)
            System.arraycopy(elementData, index + 1, elementData, index,
                    numMoved);
        elementData[--size] = null; // clear to let GC do its work

        return oldValue;
    }

    /**
     * 移除列表中第一个出现的元素o（正序）
     * 时间复杂度为O(n)
     *
     * @param o
     * @return
     */
    public boolean remove(Object o) {
        // o为null值
        if (o == null) {
            for (int index = 0; index < size; index++)
                if (elementData[index] == null) {
                    fastRemove(index);
                    return true;
                }
        } else {
            // o为数值
            for (int index = 0; index < size; index++)
                if (o.equals(elementData[index])) {
                    fastRemove(index);
                    return true;
                }
        }
        return false;
    }

    /*
     * 跳过边界检查以及无返回值
     */
    private void fastRemove(int index) {
        modCount++;
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index + 1, elementData, index,
                    numMoved);
        elementData[--size] = null; // clear to let GC do its work
    }

    /**
     * 将列表中的所有元素置空
     * 时间复杂度为O(n)
     */
    public void clear() {
        modCount++;

        // clear to let GC do its work
        for (int i = 0; i < size; i++)
            elementData[i] = null;

        size = 0;
    }

    /**
     * 序列化被填充部分的数组
     */
    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException {
        // Write out element count, and any hidden stuff
        int expectedModCount = modCount;
        s.defaultWriteObject();

        // Write out size as capacity for behavioural compatibility with clone()
        s.writeInt(size);

        // Write out all elements in the proper order.
        for (int i = 0; i < size; i++) {
            s.writeObject(elementData[i]);
        }

        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }

    /**
     * 反序列化数组
     */
    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException {
        elementData = EMPTY_ELEMENTDATA;

        // Read in size, and any hidden stuff
        s.defaultReadObject();

        // Read in capacity
        s.readInt(); // ignored

        if (size > 0) {
            // be like clone(), allocate array based upon size not capacity
            ensureCapacityInternal(size);

            Object[] a = elementData;
            // Read in all elements in the proper order.
            for (int i = 0; i < size; i++) {
                a[i] = s.readObject();
            }
        }
    }

    /**
     * 下标检测
     */
    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * 检查下标是否越界
     * 不检查下标是否为负数，若为负则抛出ArrayIndexOutOfBoundsException异常
     */
    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * 异常信息
     */
    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }


    public static void main(String[] args) {
        int a = 10;
        System.out.println(10 >> 1);

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        ((ArrayList<String>) list).ensureCapacity(11);

        List<String> list1 = list.subList(0, list.size());
        list1.add("c");
        System.out.println(list.equals(list1));
        list.add("d");
        for (int i = 0; i < list1.size(); i++) {
            String s = list1.get(i);
            System.out.println(s);
        }
    }


}
