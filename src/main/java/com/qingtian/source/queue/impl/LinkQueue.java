package com.qingtian.source.queue.impl;

import com.qingtian.source.queue.Queue;

/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/08/05
 * @description
 */
public class LinkQueue<E> implements Queue<E> {

    /**
     * 定义一个内部类Node，实现链表队列的结点
     *
     * @param <E>
     */
    private static class Node<E> {

        E item;
        Node<E> next;

        public Node() {
        }

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    /**
     * 队列头指针
     */
    private Node<E> front;
    /**
     * 队列尾指针
     */
    private Node<E> rear;
    /**
     * 队列包含的结点数
     */
    private int size;

    /**
     * 无参构造函数
     */
    public LinkQueue() {
        front = null;
        rear = null;
    }

    /**
     * 以特定的数据来构造链表队列的头结点
     *
     * @param data
     */
    public LinkQueue(E data) {

        front = new Node<>(data, null);
        rear = front;
        size++;
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 入队
     *
     * @param data
     */
    @Override
    public void push(E data) {
        Node<E> newNode = new Node<>(data, null);
        // 当前队列为空
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            // 队列存在元素
            // 尾结点的next指向新结点
            rear.next = newNode;
            // 新结点成为新的尾结点
            rear = newNode;
        }
        size++;
    }

    /**
     * 出队，并删除头元素
     *
     * @return
     * @throws IndexOutOfBoundsException
     */
    @Override
    public E pop() {

        // 检测队列是否空
        checkQueueIsEmpty();
        Node<E> oldFront = front;
        front = front.next;
        oldFront.next = null;
        size--;
        return oldFront.item;
    }

    /**
     * 查找队列的front元素
     *
     * @return
     * @throws IndexOutOfBoundsException
     */
    @Override
    public E peek() {

        // 检测队列是否空
        checkQueueIsEmpty();
        return rear.item;
    }

    @Override
    public void clear() {
        front = null;
        rear = null;
        size = 0;
    }

    /**
     * 返回队列长度
     *
     * @return
     */
    @Override
    public int length() {
        return size;
    }

    @Override
    public String toString() {

        if (isEmpty()) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (Node<E> cur = front; cur != null; cur = cur.next) {
                sb.append(cur.item.toString() + ", ");
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
}
