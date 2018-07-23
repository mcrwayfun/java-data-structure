# 面试题23 链表中环的入口结点

## 题目

如何一个链表中，找出它的环入口结点，链表结构如下
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
解决这个问题分两步来，第一步确定链表是否包含环，这个可以利用快慢指针方法来解决，如果快慢指针能够重合，则说明有环；第二步则是确定环入口在哪
1. 慢指针和快指针均从头开始，慢指针每次走一步，快指针每次走两步，使用快慢指针找到相遇的结点，快指针存储相遇点
2. 慢指针从头开始，快指针从相遇点开始，当再次相遇时，相遇点即环入口结点

![](https://i.imgur.com/JP7DTK3.jpg)

假设表头到入口点距离为x，入口点到相遇点距离为d，一个环周长为y（从入口点出发到再次遇到入口点），指针顺时针走动

以上过程可以总结为：
S慢 = x + d，那么S快 = 2*（x + d），相遇时绕环距离 = ny，
```
    S快 - S慢 = 2*（x + d） - （x + d） = ny
    x + d = ny
	x = ny - d
	x = (n-1)y + (y - d)
```
这就说明了x相当于（n-1）个周长加上y-d，即`x = y - d`（去变量n，因为与xy无关）。这就说明，当快慢指针再次相遇时，相遇点位入口点（y-d走过的路程为入口点）

```java
class Solution { 
  
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
```
分析：

- Time complexity：O(n)