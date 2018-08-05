package com.qingtian.source.queue.impl;

import com.qingtian.source.queue.Queue;

import java.util.Arrays;

/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/08/05
 * @description 循环队列
 */
public class LoopQueue<E> implements Queue<E> {

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

    public LoopQueue() {
        this.capacity = DEFAULT_SIZE;
        elementData = new Object[capacity];
    }

    /**
     * 以一个初始元素来构建循环队列
     *
     * @param data
     */
    public LoopQueue(E data) {
        this();
        elementData[0] = data;
        rear++;
    }

    /**
     * 以初始元素和初始长度来构建循环队列
     *
     * @param data
     * @param initSize
     */
    public LoopQueue(E data, int initSize) {

        this.capacity = initSize;
        elementData = new Object[capacity];
        elementData[0] = data;
        rear++;
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return rear == front
                && elementData[rear] == null;
    }

    /**
     * 入队
     *
     * @param data
     * @throws IndexOutOfBoundsException
     */
    @Override
    public void push(E data) {

        // 判断队列是否已经满了
        checkQueueIsFull();
        elementData[rear++] = data;
        // rear到头则rear转头
        rear = rear == capacity ? 0 : rear;
    }

    /**
     * 出队并删除头元素
     *
     * @return
     * @throws IndexOutOfBoundsException
     */
    @Override
    @SuppressWarnings("unchecked")
    public E pop() {

        // 判断队列是否为空
        checkQueueIsEmpty();
        E oldValue = (E) elementData[front];
        elementData[front++] = null;
        // front到头则front转头
        front = front == capacity ? 0 : front;
        return oldValue;
    }

    /**
     * 查找队列的第一个元素
     *
     * @return
     * @throws IndexOutOfBoundsException
     */
    @Override
    @SuppressWarnings("unchecked")
    public E peek() {

        // 判断队列是否为空
        checkQueueIsEmpty();
        return (E) elementData[front];
    }

    /**
     * 清空队列
     */
    @Override
    public void clear() {

        Arrays.fill(elementData, null);
        front = rear = 0;
    }

    /**
     * 获取循环队列的大小
     *
     * @return
     */
    @Override
    public int length() {

        if (isEmpty()) {
            return 0;
        } else {
            return rear - front > 0 ? rear - front
                    : capacity - (front - rear);
        }
    }

    public String toString()
    {
        if (isEmpty())
        {
            return "[]";
        }
        else
        {
            //如果front < rear，有效元素就是front到rear之间的元素
            if (front < rear)
            {
                StringBuilder sb = new StringBuilder("[");
                for (int i = front  ; i < rear ; i++ )
                {
                    sb.append(elementData[i].toString() + ", ");
                }
                int len = sb.length();
                return sb.delete(len - 2 , len).append("]").toString();
            }
            //如果front >= rear，有效元素为front->capacity之间、0->front之间的
            else
            {
                StringBuilder sb = new StringBuilder("[");
                for (int i = front  ; i < capacity ; i++ )
                {
                    sb.append(elementData[i].toString() + ", ");
                }
                for (int i = 0 ; i < rear ; i++)
                {
                    sb.append(elementData[i].toString() + ", ");
                }
                int len = sb.length();
                return sb.delete(len - 2 , len).append("]").toString();
            }
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

        if (rear == front && elementData[front] != null) {
            throw new IndexOutOfBoundsException("队列已经满了");
        }
    }
}
