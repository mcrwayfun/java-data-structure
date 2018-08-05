package com.qingtian.source.stack.impl;

/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/08/05
 * @description
 */
public class SequenceStackTest {

    public static void main(String[] args) {

        SequenceStack<Integer> stack = new SequenceStack<>();
        // 依次入栈
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        System.out.println("打印当前栈:" + stack);
        // 访问栈顶元素
        System.out.println("栈顶元素为:" + stack.peek());
        // 删除一个栈顶元素
        stack.pop();
        // 再删除一个
        stack.pop();
        System.out.println("删除两个元素后的栈为:" + stack);
    }

}
