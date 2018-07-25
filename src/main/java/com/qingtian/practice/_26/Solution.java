package com.qingtian.practice._26;

import com.qingtian.practice.pojo.RandomListNode;

/**
 * @author mcrwayfun
 * @version 1.0
 * @description 复杂链表的复制
 * @date Created in 2018/7/25
 */
public class Solution {

    public RandomListNode Clone(RandomListNode pHead) {

        if (pHead == null) {
            return null;
        }

        // 克隆next结点
        copyNextNode(pHead);
        // 克隆random结点
        copyRandomNode(pHead);
        // 将复制的链表与原链表分离
        return separate(pHead);
    }

    private void copyNextNode(RandomListNode pHead) {

        RandomListNode cur = pHead;
        // 复制原链表的next结点，使之成为
        // 1-1-2-2-3-3-4-4
        while (cur != null) {

            RandomListNode copyNode = new RandomListNode(-1);
            copyNode.label = cur.label;
            copyNode.next = cur.next;
            copyNode.random = null;
            cur.next = copyNode;

            cur = copyNode.next;
        }
    }

    private void copyRandomNode(RandomListNode pHead) {

        RandomListNode cur = pHead;
        // 复制原链表的random结点
        while (cur != null) {

            RandomListNode copyNode = cur.next;
            if (cur.random != null) {
                copyNode.random = cur.random.next;
            }

            cur = copyNode.next;
        }
    }

    private RandomListNode separate(RandomListNode pHead) {


        RandomListNode cur = pHead;
        RandomListNode copyNodeHead = null;
        RandomListNode copyNode = null;

        if (cur != null) {
            copyNodeHead = copyNode = cur.next;
            cur.next = copyNode.next;
            cur = cur.next;
        }

        while (cur != null) {

            copyNode.next = cur.next;
            copyNode = copyNode.next;
            cur.next = copyNode.next;
            cur = cur.next;
        }

        return copyNodeHead;
    }

    public static void main(String[] args) {

        RandomListNode head1 = new RandomListNode(1);
        RandomListNode head2 = new RandomListNode(2);
        RandomListNode head3 = new RandomListNode(3);
        RandomListNode head4 = new RandomListNode(4);

        head1.next = head2;
        head2.next = head3;
        head3.next = head4;

        head1.random = head3;
        head2.random = head4;
        head3.random = null;
        head4.random = null;

        RandomListNode copy = new Solution().Clone(head1);
        while (copy != null) {
            System.out.println(copy.label + " ");
            copy = copy.next;
        }

    }
}
