# 复制复杂的链表

## 题目

输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的head，链表结构如下
```
	public class RandomListNode {
    
         int label;
         RandomListNode next = null;
         RandomListNode random = null;
    
         RandomListNode(int label) {
            this.label = label;
        }
    
    }
```

**Tags:** Linked List

## 思路1 
原链表尾1-2-3-4

1. 复制next结点，并将其链接与原链表原结点后，形成1-1-2-2-3-3-4-4
2. 复制random结点，复制的random结点指向原链表random结点的next域
3. 将链表中偶数位置的结点取出，形成一个新链表

```java
class Solution { 
  
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

}
```
分析：

- Time complexity：O(n)