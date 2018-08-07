# 栈的压入，弹出序列

## 题目 

输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出序列。假设压入栈的所有数字均不相等。例如序列1，2，3，4，5是某栈的压栈序列，序列4，5，3，2，1是该栈序列对应的一个弹出序列，但4，3，5，1，2就不可能是该栈序列的弹出序列。

**Tags:** Stack

## 思路1 
1. 定义一个栈stack，对pushA中的元素进行压栈
2. 判断stack.peek() == popA[index]，若相等，则stack出栈且index++，循环执行步骤2直到判断条件不成立
3. 判断stack是否为空，若出栈顺序相同，则栈已经空了

```java
class Solution { 
  
 	 public boolean IsPopOrder(int[] pushA, int[] popA) {

        if (pushA == null || popA == null) {
            return false;
        }

        Stack<Integer> stack = new Stack<>();
        // popA下标
        int index = 0;

        // 遍历pushA并压栈
        for (int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            // 若栈顶与popA[index]相等，则出栈且index++
            while (!stack.isEmpty() && stack.peek() == popA[index]) {
                stack.pop();
                index++;
            }
        }
        // 若出栈顺序相同，则栈已经空了
        return stack.isEmpty();
    }

}
```
分析：

- Time complexity：O(n)