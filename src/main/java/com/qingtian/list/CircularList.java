package com.qingtian.list;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/07/11
 * @description
 */
public class CircularList {

    public static final Logger log = LoggerFactory.getLogger(CircularList.class);

    private Node first;
    private Node last;

    /**
     * 构造函数
     */
    public CircularList() {
        first = new Node<>("A");
        last = first;
    }

    /**
     * 创建一条循环链表
     *
     * @author mcrwayfun
     * @version 1.0
     * @data 2018/7/12
     */
    public void createCircularList() {

        if (first == null)
            first = new Node<>("A");

        Node B = new Node<>("B");
        Node C = new Node<>("C");
        Node D = new Node<>("D");
        Node E = new Node<>("E");
        Node F = new Node<>("F");
        Node G = new Node<>("G");


        first.next = B;
        B.next = C;
        C.next = D;
        D.next = E;
        E.next = F;
        F.next = G;
        last = first;
    }

    /**
     * 判断是否为空
     *
     * @return true为空
     * @author mcrwayfun
     * @version 1.0
     * @date 2018/7/12
     */
    public boolean isEmpty() {
        return this.first == null;
    }

    /**
     * 清空一个链表
     *
     * @author mcrwayfun
     * @version 1.0
     * @date 2018/7/12
     */
    public void clear() {
        this.first = null;
    }

    /**
     * 打印
     *
     * @param first 头结点
     * @author mcrwayfun
     * @version 1.0
     * @date 2018/7/12
     */
    public void print(Node first) {

        Node current = first;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    /**
     * 返回指定节点
     *
     * @param data
     * @return 指定的节点
     * @author mcrwayfun
     * @version 1.0
     * @date 2018/7/12
     */
    public Node get(String data) {

        Node current = first;
        Node temp = null;
        while (!current.data.equals(data)) {
            temp = current;
            current = current.next;
        }

        if (temp == null)
            return first;
        // 当前需要返回的指定节点
        Node newNode = temp.next;
        return newNode == null ? new Node<>("-1") : newNode;
    }

    /**
     * 在循环链表中插入一个节点
     *
     * @param node 某个节点
     * @param data 插入的节点数据
     */
    public void insert(Node node, String data) {

        Node insert = new Node<>(data);

        if (this.isEmpty()) {// 头节点插入
            insert.next = first;
            last = insert;
        } else {
            insert.next = node.next;
            node.next = insert;
        }
    }

    /**
     * Node类，用于初始化一个结点
     *
     * @param <T>
     */
    public class Node<T> {

        private T data;
        private Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

    }

    public static void main(String[] args) {

        CircularList circularList = new CircularList();
        circularList.createCircularList();

        log.info("----------------- 打印链表 ----------------------");
        circularList.print(circularList.first);

        log.info("----------------- 在链表指定位置插入一个节点 ----------------------");
        circularList.clear();
        circularList.createCircularList();
        Node C = circularList.get("A");
        circularList.insert(C, "I");
        circularList.print(circularList.first);

    }
}
