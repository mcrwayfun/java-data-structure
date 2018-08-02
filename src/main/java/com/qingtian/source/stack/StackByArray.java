package com.qingtian.source.stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mcrwayfun
 * @version 1.0
 * @description
 * @date Created in 2018/8/2
 */
public class StackByArray implements Stack {


    public static final Logger log = LoggerFactory.getLogger(StackByArray.class);

    private int[] stack;

    private int maxSize;

    /**
     * 栈顶
     */
    private int top;

    public StackByArray(int size) {
        maxSize = size;
        stack = new int[size];
        top = -1;
    }

    /**
     * 判断栈是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 判断栈是否已经满
     *
     * @return
     */
    public boolean isFull() {
        return top >= maxSize;
    }

    @Override
    public void clear() {
        stack = new int[maxSize];
        top = -1;
    }

    /**
     * 压栈
     */
    @Override
    public void push(int data) {

        if (isFull()) {
            System.out.println("栈已经满了");
            return;
        }

        stack[++top] = data;
    }


    /**
     * 出栈，并移动top元素
     *
     * @return
     */
    @Override
    public int pop() {

        if (isEmpty()) {
            System.out.println("栈为空");
            return -1;
        }

        return stack[top--];
    }

    /**
     * 出栈，但是不改变top元素
     *
     * @return
     */
    @Override
    public int peek() {

        if (isEmpty()) {
            System.out.println("栈为空");
            return -1;
        }

        return stack[top];
    }

    @Override
    public void print() {

        if(isEmpty()){
            System.out.println("栈为空");
        }

        for (int index = top; index >= 0; index--) {
            System.out.print(stack[index] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        StackByArray stack = new StackByArray(5);
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
