package com.qingtian.source.stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mcrwayfun
 * @version 1.0
 * @description
 * @date Created in 2018/8/2
 */
public class StackByLinkedList implements Stack {

    public static final Logger log = LoggerFactory.getLogger(StackByLinkedList.class);


    Node top;

    /**
     * 当前有多少个元素
     */
    int size;

    public StackByLinkedList() {
        top = null;
        size = 0;
    }

    /**
     * 判断当前链表是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * 压栈
     *
     * @param data
     */
    @Override
    public void push(int data) {
        // 从头指针处压入
        Node newNode = new Node(data, null);
        // 栈为空
        if (isEmpty()) {
            top = newNode;
        } else {
            // 栈非空
            newNode.next = top;
            top = newNode;
        }
        size++;
    }

    /**
     * 出栈，并移动top指针
     *
     * @return
     */
    @Override
    public int pop() {

        if (isEmpty()) {
            System.out.println("栈为空");
            return -1;
        }

        int data = top.item;
        top = top.next;

        size--;
        return data;
    }

    /**
     * 出栈，但不移动top指针
     *
     * @return
     */
    @Override
    public int peek() {

        if (isEmpty()) {
            System.out.println("栈为空");
            return -1;
        }

        return top.item;
    }

    /**
     * 清空栈
     */
    @Override
    public void clear() {
        for (Node x = top; x != null; ) {
            Node next = x.next;
            x.next = null;
            x = next;
        }
        top = null;
    }


    @Override
    public void print() {

        Node cur = top;
        while (cur != null) {
            System.out.print(cur.item + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public int getSize() {
        return size;
    }

    private static class Node {
        int item;
        Node next;

        public Node(int item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public static void main(String[] args) {

        StackByLinkedList stack = new StackByLinkedList();
        log.info("----------------- 栈是否为空: ----------------------");
        System.out.println("栈是否为空:" + stack.isEmpty());

        int[] datas = new int[]{1, 2, 3, 4, 5};
        stack.initData(datas);

        log.info("----------------- 打印当前栈元素: ----------------------");
        stack.print();

        log.info("----------------- 不删除元素出栈: ----------------------");
        System.out.println(stack.peek());
        System.out.println(stack.peek());

        log.info("----------------- 删除元素出栈: ----------------------");
        System.out.println(stack.pop());
        stack.print();

        log.info("----------------- 测试栈空时进行出栈操作: ----------------------");
        stack.clear();
        stack.pop();

    }

    private void initData(int[] datas) {
        clear();
        // 初始化数据
        for (int x : datas) {
            this.push(x);
        }
    }
}
