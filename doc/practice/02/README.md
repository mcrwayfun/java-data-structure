# 链表中倒数第k个结点

## 题目

输入一个链表，输出该链表中倒数第k个结点，链表结构如下
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
假设链表有n个结点，那么倒数第k个结点便是从头正数的第n-k+1个结点。

定义两个指针fast和slow，fast指针遍历到第k-1个结点。此时，slow指针从头开始，fast从第k-1个结点开始，那么遍历到第n个结点时，slow指针刚好指向倒数第k个结点。

fast指向第k-1个结点时，到fast指向第n个结点，走过的路径为n-（k-1），即n-k+1

```java
class Solution { 
  
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

}
```
分析：

- Time complexity：O(n)