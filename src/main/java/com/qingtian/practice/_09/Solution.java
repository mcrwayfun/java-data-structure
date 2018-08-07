package com.qingtian.practice._09;

import java.util.Stack;

/**
 * @author mcrwayfun
 * @version 1.0
 * @description
 * @date Created in 2018/8/7
 */
public class Solution {

    public boolean IsPopOrder(int[] pushA, int[] popA) {

        if (pushA == null || popA == null) {
            return false;
        }

        Stack<Integer> stack = new Stack<>();
        // popA下标
        int index = 0;

        // 遍历pushA并压栈
        for (int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            // 若栈顶与popA[index]相等，则出栈且index++
            while (!stack.isEmpty() && stack.peek() == popA[index]) {
                stack.pop();
                index++;
            }
        }
        // 若出栈顺序相同，则栈已经空了
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        int[] pushA = new int[]{1, 2, 3, 4, 5};
        int[] popA = new int[]{4, 5, 3, 2, 1};
        boolean result = new Solution().IsPopOrder(pushA, popA);
        System.out.println("是否为正确的出栈顺序:" + result);
    }
}
