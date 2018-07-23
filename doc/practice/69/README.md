# 面试题67 删除链表中重复的结点

## 题目 

在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5

链表结构如下
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
1. 定义一个头结点，link域指向原来的first，使其成为新的头结点。（方便删除第一个元素）
2. 定义两个结点，一个为当前结点cur，另一个则是新的头结点prev
3. 利用cur来循环遍历。若当前结点与其下一个结点值相等，则进入另一个循环，循环直到该重复结点全部删除，此时prev指向当前结点cur（cur已经移动到无重复元素的位置）；若不等，则prev移动到当前结点cur，并且cur后移

```java
class Solution { 
  
 	public ListNode deleteDuplication(ListNode pHead) {

        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        // 设置一个带结点的链表
        ListNode newHead = new ListNode(pHead.val - 1);
        // 指向头结点
        newHead.next = pHead;
        // 当前结点
        ListNode cur = pHead;
        // 先前的结点
        ListNode prev = newHead;
        while (cur != null && cur.next != null) {

            // 当前结点值等于下一个结点值
            if (cur.val == cur.next.val) {
                int val = cur.val;
                // 循环直到该重复结点全部删除
                while (cur != null && cur.val == val) {
                    cur = cur.next;
                }
                // prev.next指向当前结点
                prev.next = cur;
            } else {
                // 无重复perv直接移动
                prev = cur;
                cur =cur.next;
            }
        }

        return newHead.next;
    }

}
```
分析：

- Time complexity：O(n)