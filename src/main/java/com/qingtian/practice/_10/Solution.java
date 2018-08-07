package com.qingtian.practice._10;

import java.util.Stack;

/**
 * @author mcrwayfun
 * @version 1.0
 * @description
 * @date Created in 2018/8/7
 */
public class Solution {

    /** 正常顺序栈 */
    private Stack<Integer> stack = new Stack<>();
    /** 最小值栈 */
    private Stack<Integer> min = new Stack<>();

    /**
     * 压栈
     * @param node
     */
    public void push(int node) {

        stack.push(node);
        // min为空直接压栈
        if (min.empty()) {
            min.push(node);
        } else {
            // node值更小，则node成为新的栈顶
            if (min.peek() > node) {
                min.push(node);
            } else {
                // 压入与当前栈顶相同值
                // 保持与压入stack数量相同，方便pop
                min.push(min.peek());
            }
        }
    }

    /**
     * 出栈（两个一起出）
     */
    public void pop() {
        stack.pop();
        min.pop();
    }

    /**
     * 返回stack的栈顶元素，但是不删除
     * @return
     */
    public int top() {
        return stack.peek();
    }

    /**
     * 返回当前栈中最小值
     * @return
     */
    public int min() {
        return min.peek();
    }

    public static void main(String[] args) {

        Solution solution = new Solution();
        solution.push(3);
        solution.push(4);
        solution.push(1);
        solution.push(6);

        System.out.println("当前最小值为:" + solution.min());
    }

}
