package com.qingtian.practice.linkedlist._03;

import com.qingtian.practice.linkedlist.ListNode;
import com.qingtian.practice.linkedlist.ListNodeUtil;

/**
 * @author mcrwayfun
 * @version 1.0
 * @description 删除重复的结点
 * @date Created in 2018/7/20
 */
public class Solution {

    public ListNode deleteDuplication(ListNode pHead) {

        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        // 设置一个带结点的链表
        ListNode newHead = new ListNode(pHead.val - 1);
        newHead.next = pHead;
        // 当前结点
        ListNode cur = pHead;
        // 先前的结点
        ListNode prev = newHead;
        while (cur != null && cur.next != null) {

            // 当前结点值等于下一个结点值
            if (cur.val == cur.next.val) {
                int val = cur.val;
                // 循环知道该重复结点全部删除
                while (cur != null && cur.val == val) {
                    cur = cur.next;
                }
                // prev.next指向当前结点
                prev.next = cur;
            } else {
                // 无重复perv直接移动
                prev = cur;
                cur =cur.next;
            }
        }

        return newHead.next;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 1, 1, 1, 1, 1, 1};
        ListNode listNode = ListNodeUtil.createList(array);
        ListNode deleteDuplication = new Solution().deleteDuplication(listNode);
        ListNodeUtil.print(deleteDuplication);
    }
}
