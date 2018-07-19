package com.qingtian.list.impl;

import com.qingtian.list.MyList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mcrwayfun
 * @version 1.0
 * @description 环形链表
 * @date Created in 2018/7/17
 */
public class CircularLinkedList<E> implements MyList<E> {

    public static final Logger log = LoggerFactory.getLogger(CircularLinkedList.class);

    int size = 0;

    Node<E> first;
    Node<E> last;

    /**
     * 清空链表
     */
    @Override
    public void clear() {

        // 循环清空每个结点
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

        // 检查index是否越界或数据e是否为空
        if (checkPositionIndex(index) || assertDataNull(e)) {
            return;
        }

        Node<E> newNode = new Node<>(e, null);
        // 在头结点处插入
        if (index == 0) {
            newNode.next = first;
            first = newNode;
            last = newNode;
        } else if (index == size - 1) {
            // 在尾部插入
            newNode.next = first;
            last.next = newNode;
            last = newNode;
        } else {
            // 在中部插入
            // 获取指定位置前一个元素
            Node<E> node = node(index - 1);
            newNode.next = node.next;
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
        // 头结点不存在
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            newNode.next = first;
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
        } else if (index == size - 1) {
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

    /**
     * 获取指定元素的数据
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
     * 输出环形链表
     */
    @Override
    public void print() {

        if (isEmpty()) {
            System.out.println("链表为空");
            return;
        }

        Node<E> cur = first;

        do {

            System.out.print(cur.item + " ");
            cur = cur.next;

        } while (cur != first);

        System.out.println();
    }

    /**
     * 判断链表有没有环
     *
     * @return
     */
    public boolean hasCycle() {

        if (first == null || first.next == null) {
            return false;
        }

        Node<E> fast = first;
        Node<E> slow = first;

        while (fast.next.next != null) {

            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow)
                return true;
        }

        return false;
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
        Node<E> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        CircularLinkedList<String> list = new CircularLinkedList<>();
        log.info("----------------- 链表是否为空: ----------------------");
        System.out.println("链表是否为空:" + list.isEmpty());
        log.info("----------------- 向链表中插入元素（A-B-C-D-E）: ----------------------");
        String[] str = new String[]{"A", "B", "C", "D", "E"};
        for (String s : str) {
            list.add(s);
        }
        log.info("----------------- 在指定位置C插入一条数据F: ----------------------");
        list.add(2, "F");
        list.print();

        log.info("----------------- 获取指定位置2的数据: ----------------------");
        String data = list.get(2);
        System.out.println("index为2的数据为：" + data);

        log.info("----------------- 移除指定位置2的数据: ----------------------");
        list.remove(4);
        list.print();

        log.info("----------------- 判断链表是否存在环: ----------------------");
        System.out.println("链表list是否存在环:" + list.hasCycle());

    }
}
