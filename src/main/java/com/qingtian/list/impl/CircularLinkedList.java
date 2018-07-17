package com.qingtian.list.impl;

import com.qingtian.list.MyList;

/**
 * @author mcrwayfun
 * @version 1.0
 * @description 环形链表
 * @date Created in 2018/7/17
 */
public class CircularLinkedList<E> implements MyList<E> {

    int size = 0;

    Node<E> first;
    Node<E> last;

    /**
     * 清空链表
     */
    @Override
    public void clear() {

        // 循环清空每个节点
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x = next;
        }

        first = null;
        last = null;
        size = 0;
    }

    /**
     * 判断链表是否为空
     *
     * @return 如果链表为空则返回true
     */
    @Override
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * 在链表指定位置新增一个元素
     *
     * @param index
     * @param e
     */
    @Override
    public void add(int index, E e) {

        // 检查下标是否越界
        if (checkPositionIndex(index)) {
            return;
        }

        // 检查数据是否存在
        if (assertDataNull(e)) {
            return;
        }

        Node<E> newNode = new Node<>(e, null);
        // 在头节点处插入
        if (index == 0) {
            newNode.next = first;
            first = newNode;
            last = newNode;
        } else if (index == size) {
            // 在尾部插入
            newNode.next = first;
            last.next = newNode;
            last = newNode;
        } else {
            // 在中部插入
            // 获取指定位置前一个元素
            Node<E> node = node(index - 1);
            newNode.next = node.next.next;
            node.next = newNode;
        }

        size++;
    }

    /**
     * 在链表末尾添加元素
     *
     * @param e
     */
    @Override
    public void add(E e) {

        // 检查数据是否存在
        if (assertDataNull(e)) {
            return;
        }

        Node<E> newNode = new Node<>(e, null);
        // 头节点不存在
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            newNode.next = first;
            last.next = newNode;
            last = newNode;
        }

        size++;
    }

    /**
     * 移除指定位置的元素
     *
     * @param index
     */
    @Override
    public void remove(int index) {

        // 检查index是否越界
        if (checkPositionIndex(index)) {
            return;
        }

        // 移除头元素
        if (index == 0) {
            first = first.next;
            last = first;
        } else if (index == size) {
            // 移除尾元素
            // 获取移除元素的前一个
            Node<E> node = node(index - 1);
            node.next = first;
            last = node;
        } else {
            // 移除中间的元素
            // 获取移除元素的前一个
            Node<E> node = node(index - 1);
            node.next = node.next.next;
        }

        size--;
    }

    @Override
    public E get(int index) {
        return null;
    }

    /**
     * 获取指定位置的Node
     *
     * @param index
     * @return
     */
    Node<E> node(int index) {

        Node<E> x = first;

        for (int i = 0; i < index; i++) {
            x = x.next;
        }

        return x;
    }

    /**
     * 检查index是否越界
     *
     * @param index
     * @return 越界返回true
     */
    private boolean checkPositionIndex(int index) {

        if (index < 0 || index > size) {
            System.out.println("index越界");
            return true;
        } else {
            return false;
        }
    }

    /**
     * 检查数据是否为空
     *
     * @param e
     * @return 为空则返回true
     */
    private boolean assertDataNull(E e) {
        if (e == null) {
            System.out.println("数据不能为空");
            return true;
        } else {
            return false;
        }
    }

    private static class Node<E> {
        E item;
        Node<E> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }
}
