package com.qingtian.practice._02;

import com.qingtian.practice.pojo.ListNode;
import com.qingtian.practice.util.ListNodeUtil;

/**
 * @author mcrwayfun
 * @version 1.0
 * @description 链表中倒数第k个结点
 * @date Created in 2018/7/20
 */
public class Solution {

    public ListNode FindKthToTail(ListNode head, int k) {

        if (head == null || k <= 0) {
            return null;
        }

        ListNode fast = head, slow = head;
        // 遍历到第k-1的结点
        for (int i = 1; i <= k - 1; i++) {

            if (fast.next != null) {
                fast = fast.next;
            }else{
                return null;
            }
        }


        // slow从头开始遍历，fast从k-1开始遍历
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
        ListNode findKthToTail = new Solution().FindKthToTail(listNode, k);
        ListNodeUtil.print(findKthToTail);
    }
}
