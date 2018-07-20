package com.qingtian.practice.linkedlist._04;

import com.qingtian.practice.linkedlist.ListNode;
import com.qingtian.practice.linkedlist.ListNodeUtil;

/**
 * @author mcrwayfun
 * @version 1.0
 * @description 两个链表的第一个公共结点
 * @date Created in 2018/7/20
 */
public class Solution {


    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {

        if (pHead1 == null || pHead2 == null) {
            return null;
        }

        ListNode a = pHead1, b = pHead2;
        while (a != b) {

            if (a != null && b != null && a.val == b.val) {
                return a;
            }

            a = (a == null ? pHead1 : a.next);
            b = (b == null ? pHead2 : b.next);
        }

        return a;
    }

    public static void main(String[] args) {
        int[] arrayA = new int[]{1, 3, 5, 7, 9, 11};
        int[] arrayB = new int[]{2, 4, 8, 9,11};
        ListNode pHead1 = ListNodeUtil.createList(arrayA);
        ListNode pHead2 = ListNodeUtil.createList(arrayB);
        ListNode listNode = new Solution().FindFirstCommonNode(pHead1, pHead2);
        ListNodeUtil.print(listNode);
    }
}
