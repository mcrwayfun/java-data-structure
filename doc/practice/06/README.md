# 面试题6 从尾到头打印链表

## 题目

输入一个链表的头结点，从尾到头反过来打印出每个结点的值，返回一个ArrayList，链表结构如下
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
链表遍历是从头到尾，题目要求从尾到头打印每个结点值，这是典型的后进先出，所以我们利用栈来实现算法。

```java
class Solution { 
  
 	public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {

        if (listNode == null) {
            return new ArrayList<>();
        }

        Stack<Integer> stack = new Stack<>();
        ListNode cur = listNode;
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }

        ArrayList<Integer> list = new ArrayList<>();
        while (!stack.empty()) {
            Integer val = stack.pop();
            list.add(val);
        }

        return list;
    }

}
```
分析：

- Time complexity：O(n)