package com.qingtian.practice._24;

import com.qingtian.practice.pojo.ListNode;
import com.qingtian.practice.util.ListNodeUtil;

/**
 * @author mcrwayfun
 * @version 1.0
 * @description 翻转链表
 * @date Created in 2018/7/20
 */
public class Solution {

    public ListNode ReverseList(ListNode head) {

        // 头指针为空或者仅有一个结点
        if (head == null || head.next == null) {
            return head;
        }

        ListNode cur = head;
        ListNode prev = null;
        ListNode pReversedHead = null;
        while (cur != null) {

            // 保存当前结点的下一个结点
            ListNode next = cur.next;
            // 头指针重定向
            if(next == null){
                pReversedHead = cur;
            }
            // 当前指针转向，指向前一个结点
            cur.next = prev;
            // 前结点移动到当前结点
            prev = cur;
            // 当前结点移动
            cur = next;
        }

        return pReversedHead;
    }

    public static void main(String[] args) {

        int[] array = new int[]{1,2,3,4,5};
        ListNode listNode = ListNodeUtil.createList(array);
        ListNode reverseListNode = new Solution().ReverseList(listNode);
        ListNodeUtil.print(reverseListNode);
    }
}
