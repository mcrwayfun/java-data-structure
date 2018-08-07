package com.qingtian.practice._05;

import com.qingtian.practice.pojo.ListNode;
import com.qingtian.practice.util.ListNodeUtil;

/**
 * @author mcrwayfun
 * @version 1.0
 * @description 合并两个已经排序的链表
 * @date Created in 2018/7/20
 */
public class Solution {

    public ListNode Merge(ListNode list1, ListNode list2) {

        if (list1 == null) {
            return list2;
        }

        if (list2 == null) {
            return list1;
        }

        ListNode pMergeHead = null;

        // list1小则list1作头
        if (list1.val < list2.val) {
            pMergeHead = list1;
            pMergeHead.next = Merge(list1.next, list2);
        } else {
            // list2小于等于list1，则list2作头
            pMergeHead = list2;
            pMergeHead.next = Merge(list1, list2.next);
        }

        return pMergeHead;
    }

    public static void main(String[] args) {
        int[] arrayA = new int[]{1, 3, 5, 7, 9, 11};
        int[] arrayB = new int[]{2, 4, 8, 9, 11};
        ListNode list1 = ListNodeUtil.createList(arrayA);
        ListNode list2 = ListNodeUtil.createList(arrayB);
        ListNode merge = new Solution().Merge(list1, list2);
        ListNodeUtil.print(merge);
    }
}
