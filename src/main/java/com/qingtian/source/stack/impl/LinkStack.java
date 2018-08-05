package com.qingtian.source.stack.impl;

import com.qingtian.source.stack.Stack;

/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/08/05
 * @description 使用链表实现栈
 */
public class LinkStack<E> implements Stack<E> {

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
     * 当前栈内元素
     */
    private int size;

    /**
     * 栈顶
     */
    private Node<E> top;

    public LinkStack() {
        top = null;
        size = 0;
    }

    /**
     * 以默认元素来构建栈
     *
     * @param data
     */
    public LinkStack(E data) {
        this();
        Node<E> newNode = new Node<>(data, null);
        newNode.next = top;
        top = newNode;
        size++;
    }

    /**
     * 判断栈是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 压栈
     *
     * @param data
     */
    @Override
    public void push(E data) {

        Node<E> newNode = new Node<>(data, null);
        newNode.next = top;
        top = newNode;
        size++;
    }

    /**
     * 出栈，并删除栈顶元素
     *
     * @return
     * @throws IndexOutOfBoundsException
     */
    @Override
    public E pop() {

        // 检查栈是否为空
        checkIsEmpty();
        Node<E> node = top;
        // top指针移动
        top = top.next;
        // 删除栈顶元素
        node.next = null;
        size--;
        return node.item;
    }

    /**
     * 查询栈顶元素
     *
     * @return
     * @throws IndexOutOfBoundsException
     */
    @Override
    public E peek() {

        // 检查栈是否为空
        checkIsEmpty();
        return top.item;
    }

    @Override
    public void clear() {
        for (Node<E> node = top; node != null; ) {
            Node<E> next = node.next;
            node.item = null;
            node = next;
        }

        size = 0;
    }

    @Override
    public int length() {
        return size;
    }

    @Override
    public String toString() {
        //链栈为空链栈时
        if (isEmpty()) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (Node current = top; current != null
                    ; current = current.next) {
                sb.append(current.item.toString() + ", ");
            }
            int len = sb.length();
            return sb.delete(len - 2, len).append("]").toString();
        }
    }

    /**
     * 栈为空则抛出异常
     */
    private void checkIsEmpty() {

        if (isEmpty()) {
            throw new IndexOutOfBoundsException("当前栈为空");
        }
    }


}
