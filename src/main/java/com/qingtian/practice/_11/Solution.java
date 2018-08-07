package com.qingtian.practice._11;

import java.util.Stack;

/**
 * @author mcrwayfun
 * @version 1.0
 * @description 用两个栈来实现队列
 * @date Created in 2018/8/7
 */
public class Solution {

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {

        if (stack2.empty()) {

            while (!stack1.empty()) {
                int pop = stack1.pop();
                stack2.push(pop);
            }
        }

        if (stack2.empty()) {
            // throw exception
        }

        int pop = stack2.pop();
        return pop;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.push(1);
        solution.push(2);
        solution.push(3);

        System.out.println(solution.pop());
        System.out.println(solution.pop());
        System.out.println(solution.pop());

    }
}
