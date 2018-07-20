package com.qingtian.practice.linkedlist._05;

import com.qingtian.practice.linkedlist.ListNode;
import com.qingtian.practice.linkedlist.ListNodeUtil;

/**
 * @author mcrwayfun
 * @version 1.0
 * @description 链表中倒数第k个结点
 * @date Created in 2018/7/20
 */
public class Solution1 {

    public ListNode FindKthToTail(ListNode head, int k) {

        if (head == null || k < 0) {
            return null;
        }

        ListNode fast = head, slow = head;
        while (k - 1 != 0) {
            if (fast.next != null) {
                fast = fast.next;
            } else {
                return null;
            }
            k--;
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;

    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7};
        ListNode listNode = ListNodeUtil.createList(array);
        int k = 3;
        ListNode findKthToTail = new Solution1().FindKthToTail(listNode, k);
        ListNodeUtil.print(findKthToTail);
    }
}
