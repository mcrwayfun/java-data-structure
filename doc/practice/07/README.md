# 两个链表的第一个公共结点

## 题目

输入两个链表，找出他们的第一个公共结点，链表结构如下
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
如果链表存在公共结点，那么从这一个结点后，所有的结点应该都相同
1. 获取链表pHead1和pHead2的长度
2. 判断哪个长度长，使长的链表移动一定的步数，使得链表pHead1和pHead2此时具有相同的长度
3. 遍历两个链表，找出共同的结点

```java
class Solution { 
  
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

}
```
分析：

- Time complexity：O(n+m)

## 思路2 
如果两个链表本身不存在环，那么可以使用环的思想来解决问题：遍历a链表和b链表，当a链表或b链表遍历结束后，将链接到b或a表头，继续遍历。
这种算法只有两种结果：a和b没有公共结点，则遍历完a和b后返回null（因为走过的路程是相同的，都是n+m）；有共同结点则返回

```java
class Solution { 
  
 	public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
    
            if (pHead1 == null || pHead2 == null) {
                return null;
            }
    
            ListNode a = pHead1, b = pHead2;
            while (a != b) {
    
                if (a != null && b != null && a.val == b.val) {
                    return a;
                }
    
                a = (a == null ? pHead1 : a.next);
                b = (b == null ? pHead2 : b.next);
            }
    
            return a;
        }

}
```
分析：

- Time complexity：O(n+m)