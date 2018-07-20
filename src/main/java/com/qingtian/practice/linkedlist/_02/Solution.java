package com.qingtian.practice.linkedlist._02;

import com.qingtian.practice.linkedlist.ListNode;
import com.qingtian.practice.linkedlist.ListNodeUtil;

/**
 * @author mcrwayfun
 * @version 1.0
 * @description 翻转链表
 * @date Created in 2018/7/20
 */
public class Solution {

    public ListNode ReverseList(ListNode head) {

        if (head == null) {
            return null;
        }

        ListNode cur = head;
        ListNode prev = null;
        while (cur != null) {
            ListNode temp = prev;
            prev = cur;
            cur = cur.next;
            prev.next = temp;
        }

        head = prev;

        return head;
    }

    public static void main(String[] args) {

        int[] array = new int[]{1,2,3,4,5};
        ListNode listNode = ListNodeUtil.createList(array);
        ListNode reverseListNode = new Solution().ReverseList(listNode);
        ListNodeUtil.print(reverseListNode);
    }
}
