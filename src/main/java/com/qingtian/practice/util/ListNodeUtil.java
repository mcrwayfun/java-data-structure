package com.qingtian.practice.linkedlist;

/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/07/15
 * @description
 */
public class ListNodeUtil {

    /**
     * 输出一个单链表
     *
     * @param node
     */
    public static void print(ListNode node) {
        ListNode current = node;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }

    /**
     * 创建一个单链表
     *
     * @param array 输入数组
     * @return
     */
    public static ListNode createList(int[] array) {

        ListNode head = null;
        for (int i = 0; i < array.length; i++) {
            int val = array[i];
            head = add(head,val);
        }

        return head;
    }

    private static ListNode add(ListNode head, int data) {

        ListNode insNode = new ListNode(data);
        if(head == null){
            head = insNode;
            return head;
        }

        ListNode temp = head;
        ListNode cur = null;
        while (temp != null){
            cur = temp;
            temp = temp.next;
        }
        cur.next = insNode;

        return head;
    }
}
