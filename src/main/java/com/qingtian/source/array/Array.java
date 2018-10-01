package com.qingtian.source.array;

/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/10/01
 * @description
 */
public class Array {

    // 保存元素使用的数组
    private int[] data;
    // 数组中元素的个数
    private int size;

    public Array(int capacity) {
        data = new int[capacity];
        this.size = 0;
    }

    public Array() {
        this(10);
    }

    // 获取容量
    public int getCapacity() {
        return data.length;
    }

    // 获取当前数组元素
    public int getSize() {
        return size;
    }

    // 判断数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 向数组的末尾添加元素
    void addLast(int e) {
        add(size, e);
    }

    // 向数组的头部添加元素
    void addFirst(int e) {
        add(0, e);
    }

    // 在指定位置添加元素
    void add(int index, int e) {

        if (size == data.length)
            throw new IllegalStateException("Array is full !");

        if (index < 0 || index > size)
            throw new IllegalStateException("index is invalid !");

        for (int i = index; i < data.length; i++)
            data[i + 1] = data[i];

        data[index] = e;
        size++;
    }
}
