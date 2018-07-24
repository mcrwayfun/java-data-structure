package com.qingtian.practice._06;

import com.qingtian.practice.pojo.ListNode;
import com.qingtian.practice.util.ListNodeUtil;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author mcrwayfun
 * @version 1.0
 * @description 从尾到头打印链表
 * @date Created in 2018/7/20
 */
public class Solution {

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {

        if (listNode == null) {
            return new ArrayList<>();
        }

        Stack<Integer> stack = new Stack<>();
        ListNode cur = listNode;
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }

        ArrayList<Integer> list = new ArrayList<>();
        while (!stack.empty()) {
            Integer val = stack.pop();
            list.add(val);
        }

        return list;
    }


    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5};
        ListNode listNode = ListNodeUtil.createList(array);
        ArrayList<Integer> integers = new Solution().printListFromTailToHead(listNode);
        for (int x : integers) {
            System.out.print(x + " ");
        }
    }
}
