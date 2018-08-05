package com.qingtian.source.queue.impl;

import com.qingtian.source.queue.Queue;

import java.util.Arrays;

/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/08/04
 * @description 数组构建队列
 */
public class SequenceQueue<E> implements Queue<E> {

    // 队列默认长度
    private int DEFAULT_SIZE = 10;

    // 用来保存队列元素的数组
    private Object[] elementData;
    // 保存数组的长度。
    private int capacity;
    // 队列头元素位置
    private int front = 0;
    // 队列尾元素位置
    private int rear = 0;

    /**
     * 初始化数组长度和数组
     */
    public SequenceQueue() {
        this.capacity = DEFAULT_SIZE;
        elementData = new Object[capacity];
    }

    /**
     * 以一个初始元素来构建队列
     *
     * @param value
     */
    public SequenceQueue(E value) {
        this();
        elementData[0] = value;
        rear++;
    }

    /**
     * 以指定长度的数组来创建队列
     *
     * @param value    指定顺序中的第一个元素
     * @param initSize 数组长度
     */
    public SequenceQueue(E value, int initSize) {
        this.capacity = initSize;
        elementData = new Object[capacity];
        elementData[0] = value;
        rear++;
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 入队
     *
     * @param data
     * @throws IndexOutOfBoundsException
     */
    @Override
    public void push(E data) {

        // 检查队列是否已经满了
        checkQueueIsFull();

        elementData[rear++] = data;
    }

    /**
     * 出队，推出元素
     *
     * @return
     * @throws IndexOutOfBoundsException
     */
    @Override
    @SuppressWarnings("unchecked")
    public E pop() {

        // 检查队列是否为空
        checkQueueIsEmpty();

        E oldValue = (E) elementData[front];
        // 释放队列已经出栈的元素
        elementData[front++] = null;
        return oldValue;
    }

    /**
     * 推出元素但不出队
     *
     * @return
     * @throws IndexOutOfBoundsException
     */
    @Override
    @SuppressWarnings("unchecked")
    public E peek() {

        // 检查队列是否为空
        checkQueueIsEmpty();

        return (E) elementData[front];
    }

    /**
     * 清空队列中的元素
     */
    @Override
    public void clear() {
        // 将所有元素赋值为null
        Arrays.fill(elementData, null);
        front = rear = 0;
    }

    /**
     * 获取顺序队列的大小
     *
     * @return
     */
    @Override
    public int length() {
        return rear - front;
    }

    public String toString() {

        if (isEmpty()) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (int i = front; i < rear; i++) {
                sb.append(elementData[i].toString() + ", ");
            }
            int len = sb.length();
            return sb.delete(len - 2, len).append("]").toString();
        }
    }

    /**
     * 判断队列是否为空，若为空则抛出IndexOutOfBoundsException
     */
    private void checkQueueIsEmpty() {

        if (isEmpty()) {
            throw new IndexOutOfBoundsException("队列为空");
        }

    }

    /**
     * 判断队列是否已经满了，若满了则抛出IndexOutOfBoundsException
     */
    private void checkQueueIsFull() {

        if (rear > capacity - 1) {
            throw new IndexOutOfBoundsException("队列已经满了");
        }
    }

}
