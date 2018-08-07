# 合并两个排序的链表

## 题目

输入两个递增排序的链表，合并两个链表并使新链表中的结点仍然是递增排序，链表结构如下
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
当list1的值小于list2的值，则list1作为头，并将其链接到已经排好序的链表中；相反则list2作为头，链接到已经排好序的链表中。这是一个典型的递归过程

```java
class Solution { 
  
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

}
```
分析：

- Time complexity：O(n)