# 面试题24 反转链表

## 题目

定义一个函数，输入一个链表的头结点，反转该链表并输出反转后的链表的头结点，链表结构如下
```
	public class ListNode {

    	int val;
    	ListNode next = null;

    	public ListNode(int val) {
        	this.val = val;
    	}
	}
```

**Tags:** Linked List

## 思路1 
反转链表需要三个结点，假设当前结点为i，需要前一个结点i-1结点（用于转向）和后一个结点i+1结点（防止断链以及移动当前结点）

```java
class Solution { 
  
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

}
```
分析：

- Time complexity：O(n)