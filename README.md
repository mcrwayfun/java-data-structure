# 前言
由于在面试过程中，有关数据结构的题都回答的不是很好，再加上工作和学习的过程中（阅读源码），或多或少会接触到数据结构。所以我狠心下来重新拾起数据结构。以下记录我在学习数据结构的过程中的点滴，包括学习资料，文档以及输出的代码，希望能够帮助到有心人。

学习过程可以概括为：**选择合适书阅读了解基本概念 -> 编程实践 -> 阅读jdk相关源码 -> 完善实践源码 -> 刷题：剑指offer或者leetCode**

## 书籍的选择
> [数据结构-用面向对象方法和C++描述-殷人昆](https://book.douban.com/subject/2162035/)

本书是清华大学计算机系列教材，亦是我的大学数据结构启蒙课本，故选择它来了解基本概念

> [图解数据结构-使用Java-胡昭民](https://book.douban.com/subject/26786098/)

因为想用Java实现数据结构，所以还选了另外一本书籍作参考，但是...本书不作推荐，已经弃了

> [疯狂Java-程序员的基本修养-李刚](https://book.douban.com/subject/20471013/)
 
本书的数据结构部分讲得还不错，让我受益匪浅，**推荐**

> [数据结构与算法分析-韦斯](https://book.douban.com/subject/3351237/)

本书是国外数据结构与算法分析方面的经典教材，作者并未在基本数据结构上花大力气，其着重在对这些数据结构的应用上，每一个结论都给出了详尽的数学证明，**推荐**

## 编程实践
> 树的基本概念 && 实现二叉树基本操作

- [x] [文档][001]
- [x] [源码][002]

> 链表的基本概念 && 实现单向链表、循环链表和双向链表

- [x] [文档][003]
- [x] [单向链表][004]
- [x] [循环链表][005]
- [x] [双向链表][006]

> 栈的基本概念 && 实现栈的基本操作（顺序栈和链式栈）

- [x] 文档[007]
- [x] [顺序栈][008]
- [x] [链式栈][009]

> 队列的基本概念 && 实现队列的基本操作（顺序队列、链式队列和循环队列）

- [ ] 文档
- [x] [顺序队列][011]
- [x] [链式队列][012]
- [x] [循环队列][013]

## 阅读jdk源码
> LinkedList

- [x] [源码][014]

> ArrayList

- [ ] 源码

## 刷题：剑指offer或者leetCode
> 剑指offer，刷题可以使用[牛客网](https://www.nowcoder.com/ta/coding-interviews?page=1)

- [ ] [剑指offer汇总-持续更新][016]

> leetCode，刷题可以使用[leetCode官网](https://leetcode.com/)

- [ ] [leetCode汇总-持续更新][017]


    
[001]:./doc/source/tree/树.md
[002]:./src/main/java/com/qingtian/source/tree/BinaryTree.java
[003]:./doc/source/linkedList/链表.md
[004]:./src/main/java/com/qingtian/source/list/impl/SinglyLinkedList.java
[005]:./src/main/java/com/qingtian/source/list/impl/CircularLinkedList.java
[006]:./src/main/java/com/qingtian/source/list/impl/DoubleLinkedList.java
[007]:./doc/source/stack/栈.md
[008]:./src/main/java/com/qingtian/source/stack/impl/SequenceStack.java
[009]:./src/main/java/com/qingtian/source/stack/impl/LinkStack.java
[011]:./src/main/java/com/qingtian/source/queue/impl/SequenceQueue.java
[012]:./src/main/java/com/qingtian/source/stack/impl/LinkQueue.java
[013]:./src/main/java/com/qingtian/source/stack/impl/LoopQueue.java
[014]:./src/main/java/com/qingtian/core/MyLinkedList.java
[016]:./doc/practice/README.md
[017]:https://github.com/mcrwayfun/java-leet-code