package com.qingtian.list.impl;

import com.qingtian.list.MyList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/07/16
 * @description 单链表
 */
public class SinglyLinkedList<E> implements MyList<E> {

    public static final Logger log = LoggerFactory.getLogger(SinglyLinkedList.class);

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
     * 为链表添加一个元素
     *
     * @param index 指定位置
     * @param e     数据
     */
    @Override
    public void add(int index, E e) {

        // 检查index是否越界或数据e是否为空
        if (checkPositionIndex(index) || assertDataNull(e)) {
            return;
        }

        Node<E> newNode = new Node<>(e, null);

        // 在头结点插入
        if (index == 0) {
            newNode.next = first;
            first = newNode;
        } else {
            // 在中间和末尾结点插入
            // 获取index的前一个数据
            Node<E> node = node(index - 1);
            newNode.next = node.next;
            node.next = newNode;
        }

        size++;
    }

    /**
     * 在链表尾添加一个元素
     *
     * @param e
     */
    @Override
    public void add(E e) {

        // 检查数据e是否为空
        if (assertDataNull(e)) {
            return;
        }

        Node<E> newNode = new Node<>(e, null);

        // 获取当前末尾结点
        // 如果头结点不存在则创建
        if (isEmpty()) {
            first = newNode;
        } else {
            // 获取到需要添加的头一个
            Node<E> node = node(size - 1);
            node.next = newNode;
        }

        size++;
    }

    /**
     * 移除一个指定元素
     *
     * @param index
     */
    @Override
    public void remove(int index) {

        if (checkPositionIndex(index)) {
            return;
        }

        // 移除头元素
        if (index == 0) {
            first = first.next;
        } else {
            // 移除中间或者尾部元素
            // 获取index的前一个数据
            Node<E> delNode = node(index - 1);
            delNode.next = delNode.next.next;
        }

        size--;
    }

    /**
     * 获取指定位置的数据
     *
     * @param index
     * @return
     */
    @Override
    public E get(int index) {

        // 检查下标是否越界
        if (checkPositionIndex(index)) {
            return null;
        }

        return node(index).item;
    }

    /**
     * 打印链表
     */
    @Override
    public void print() {
        Node<E> cur = first;
        while (cur != null) {
            System.out.print(cur.item + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    /**
     * 反转一个单链表
     */
    public void reverse() {

        Node<E> cur = first;
        Node<E> prev = null;
        while (cur != null) {
            Node<E> temp = prev;
            prev = cur;
            cur = cur.next;
            prev.next = temp;
        }

        first = prev;
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

    private static class Node<E> {
        E item;
        Node<E> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    public static void main(String[] args) {

        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        log.info("----------------- 链表是否为空: ----------------------");
        System.out.println("链表是否为空:" + list.isEmpty());

        log.info("----------------- 向链表中插入元素（A-B-C-D-E）: ----------------------");
        String[] str = new String[]{"A", "B", "C", "D", "E"};
        for (String s : str) {
            list.add(s);
        }
        list.print();

        log.info("----------------- 在指定位置C插入一条数据F: ----------------------");
        list.add(2, "F");
        list.print();

        log.info("----------------- 获取指定位置2的数据: ----------------------");
        String data = list.get(2);
        System.out.println("index为2的数据为：" + data);

        log.info("----------------- 移除指定位置2的数据: ----------------------");
        list.remove(2);
        list.print();

        log.info("----------------- 反转一个链表: ----------------------");
        list.reverse();
        list.print();
    }
}
