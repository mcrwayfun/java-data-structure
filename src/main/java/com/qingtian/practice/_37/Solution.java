package com.qingtian.practice._37;

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
        // 获取两个链表长度
        int length1 = getLength(pHead1);
        int length2 = getLength(pHead2);
        // 长度差值
        int lengthDif = length1 - length2;

        ListNode headLong = pHead1;
        ListNode headShort = pHead2;
        // 判断哪个链表更长
        if (length1 < length2) {
            headLong = pHead2;
            headShort = pHead1;

            lengthDif = length2 - length1;
        }

        // 长的链表先走n步，直到与短的链表长度相同
        for (int i = 0; i < lengthDif; i++) {
            headLong = headLong.next;
        }

        // 找到公共结点
        while (headLong != null && headShort != null) {

            if(headLong.val == headShort.val){
                return headShort;
            }

            headLong = headLong.next;
            headShort = headShort.next;
        }

        return null;
    }

    private int getLength(ListNode node) {

        ListNode cur = node;
        int length = 0;
        while (cur != null) {
            ++length;
            cur = cur.next;
        }

        return length;
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
