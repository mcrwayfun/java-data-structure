package com.qingtian.practice.linkedlist._06;

import com.qingtian.practice.linkedlist.ListNode;
import com.qingtian.practice.linkedlist.ListNodeUtil;

/**
 * @author mcrwayfun
 * @version 1.0
 * @description 合并两个已经排序的链表
 * @date Created in 2018/7/20
 */
public class Solution {

    public ListNode Merge(ListNode list1, ListNode list2) {

        if (list1 == null && list2 == null) {
            return null;
        }

        ListNode a = list1, b = list2;
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (a != null && b != null) {

            if (a.val < b.val) {
                cur.next = a;
                // a链表值小，a移动
                a = a.next;
            } else {
                cur.next = b;
                // b链表值小，b移动
                b = b.next;
            }
            // 合并链表移动
            cur = cur.next;
        }

        // 遍历结束后，a或者b两者可能有不为空情况
        cur.next = (a != null) ? a : b;
        return dummy.next;
    }

    public static void main(String[] args) {
        int[] arrayA = new int[]{1, 3, 5, 7, 9, 11};
        int[] arrayB = new int[]{2, 4, 8, 9,11};
        ListNode list1 = ListNodeUtil.createList(arrayA);
        ListNode list2 = ListNodeUtil.createList(arrayB);
        ListNode merge = new Solution().Merge(list1, list2);
        ListNodeUtil.print(merge);
    }
}
