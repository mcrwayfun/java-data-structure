package com.qingtian.practice.linkedlist._05;

import com.qingtian.practice.linkedlist.ListNode;
import com.qingtian.practice.linkedlist.ListNodeUtil;

/**
 * @author mcrwayfun
 * @version 1.0
 * @description 链表中倒数第k个结点
 * @date Created in 2018/7/20
 */
public class Solution {

    public ListNode FindKthToTail(ListNode head, int k) {

        if (head == null || k < 0) {
            return null;
        }

        int count = 0;
        ListNode cur = head;
        while (cur != null) {
            count++;
            cur = cur.next;
        }

        int index = 1;
        cur = head;
        while (cur != null) {
            // 倒数第k个就是正数length-k+1
            if (index == (count - k + 1)) {
                return cur;
            }
            index++;
            cur = cur.next;
        }

        return null;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7};
        ListNode listNode = ListNodeUtil.createList(array);
        int k = 3;
        ListNode findKthToTail = new Solution().FindKthToTail(listNode, k);
        ListNodeUtil.print(findKthToTail);
    }
}
