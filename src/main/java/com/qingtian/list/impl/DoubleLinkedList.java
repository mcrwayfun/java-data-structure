package com.qingtian.list.impl;

import com.qingtian.list.MyList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/07/17
 * @description
 */
public class DoubleLinkedList<E> implements MyList<E> {

    public static final Logger log = LoggerFactory.getLogger(DoubleLinkedList.class);

    int size = 0;

    Node<E> first;

    /**
     * 清空链表
     */
    @Override
    public void clear() {
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }

        first = null;
        size = 0;
    }

    /**
     * 判断链表是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * 在链表的制定位置添加一个元素
     *
     * @param index
     * @param e
     */
    @Override
    public void add(int index, E e) {

        // 检查index是否越界或数据e是否为空
        if (checkPositionIndex(index) || assertDataNull(e)) {
            return;
        }

        Node<E> newNode = new Node<>(e, null, null);
        // 表头为空，添加一个头元素
        if (index == 0) {

            // 表头为空
            if (isEmpty()) {
                first = newNode;
            }
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
        } else if (index == size - 1) {
            // 在末尾添加
            Node<E> node = node(index - 1);
            node.next = newNode;
            newNode.prev = node;
        } else {
            // 在中部添加
            // 获取添加元素的前一个
            Node<E> node = node(index - 1);
            newNode.next = node.next;
            node.next.prev = newNode;
            node.next = newNode;
            newNode.prev = node;
        }

    }

    /**
     * 在链表中添加一个元素
     *
     * @param e
     */
    @Override
    public void add(E e) {

        // 检查数据e是否为空
        if (assertDataNull(e)) {
            return;
        }

        Node<E> newNode = new Node<>(e, null, null);

        // 头结点不存在
        if (isEmpty()) {
            first = newNode;
        } else {
            // 在尾部新增
            // 获取新增元素的前一个
            Node<E> node = node(size - 1);
            node.next = newNode;
            newNode.prev = node;
        }

        size++;
    }

    /**
     * 移除链表中指定位置的元素
     *
     * @param index
     */
    @Override
    public void remove(int index) {

        // 检查下标是否越界
        if (checkPositionIndex(index)) {
            return;
        }

        // 移除头结点
        if (index == 0) {
            first.next.prev = null;
            first = first.next;
        } else if (index == size - 1) {
            // 移除尾部元素
            Node<E> node = node(index - 1);
            node.next = null;
        } else {
            // 移除中部元素
            Node<E> node = node(index - 1);
            node.next = node.next.next;
            node.next.next.prev = node;
        }

        size--;
    }

    @Override
    public E get(int index) {

        // 检查下标是否越界
        if (checkPositionIndex(index)) {
            return null;
        }

        return node(index).item;
    }

    @Override
    public void print() {
        Node<E> cur = first;
        while (cur != null) {
            System.out.printf(cur.item + " ");
            cur = cur.next;
        }
        System.out.println();
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

        if (index < 0 || index >= size) {
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
        Node<E> prev;
        Node<E> next;

        public Node(E item, Node<E> prev, Node<E> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    public static void main(String[] args) {

        DoubleLinkedList<String> list = new DoubleLinkedList<>();
        log.info("----------------- 链表是否为空: ----------------------");
        System.out.println("链表是否为空:" + list.isEmpty());

        log.info("----------------- 向链表中插入元素（A-B-C-D-E）: ----------------------");
        String[] str = new String[]{"A", "B", "C", "D", "E"};
        for (String s : str) {
            list.add(s);
        }
        list.print();

        log.info("----------------- 在指定位置C插入一条数据F: ----------------------");
        list.add(4, "F");
        list.print();

        log.info("----------------- 获取指定位置2的数据: ----------------------");
        String data = list.get(2);
        System.out.println("index为2的数据为：" + data);

        log.info("----------------- 移除指定位置2的数据: ----------------------");
        list.remove(2);
        list.print();

    }
}
