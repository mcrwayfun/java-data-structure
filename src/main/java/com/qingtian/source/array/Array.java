package com.qingtian.source.array;

import java.util.Arrays;

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
            throw new IllegalArgumentException("Add failed.Array is full !");

        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed.index is invalid !");

        for (int i = size - 1; i >= index; i--)
            data[i + 1] = data[i];

        data[index] = e;
        size++;
    }

    // 获取指定位置的元素
    public int get(int index) {

        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Add failed.index is invalid !");

        return data[index];
    }

    // 替换指定位置的元素
    public void set(int index, int e) {

        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed.index is invalid !");

        data[index] = e;
    }

    // 判断数组中是否包含该元素
    public boolean contains(int e) {

        for (int x : data) {
            if (x == e) {
                return true;
            }
        }
        return false;
    }

    // 查找元素e在数组中对应的索引，如果不存在则返回 -1
    public int find(int e) {

        for (int x : data) {
            if (x == e) {
                return x;
            }
        }
        return -1;
    }

    // 删除数组中指定索引的元素
    public int remove(int index) {

        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed.index is invalid !");

        int ret = data[index];
        for (int i = index; i <= size - 1; i++)
            data[i] = data[i + 1];
        size--;

        return ret;
    }

    // 从数组中删除最后一个元素
    public void removeLast() {
        remove(size - 1);
    }

    // 从数组中删除元素e
    public void removeFirst() {
        remove(0);
    }

    // 删除数组中指定的元素e
    public void removeElement(int e) {

        int index = find(e);
        if (index != -1)
            remove(index);
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
        res.append('[');
        for(int i = 0 ; i < size ; i ++){
            res.append(data[i]);
            if(i != size - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }
}
