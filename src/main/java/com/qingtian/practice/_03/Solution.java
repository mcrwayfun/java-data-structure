package com.qingtian.practice._03;

import com.qingtian.practice.pojo.ListNode;

/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/07/21
 * @description 链表中环的入口结点
 */
public class Solution {


    public ListNode EntryNodeOfLoop(ListNode pHead) {

        if (pHead == null) {
            return null;
        }

        // 快慢指针
        ListNode fast = pHead;
        ListNode slow = pHead;
        boolean hasCycle = false;
        // 利用快慢指针判断链表是否有环
        // 若快慢指针相遇则有环
        while (fast != null && slow != null) {

            slow = slow.next;
            if (fast.next == null) {
                return null;
            }
            fast = fast.next.next;

            if (fast == slow) {
                hasCycle = true;
                break;
            }
        }

        // 不存在环 直接返回
        if (!hasCycle) {
            return null;
        }

        // 慢指针从头开始，快指针从相遇的地方开始
        slow = pHead;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }


}
