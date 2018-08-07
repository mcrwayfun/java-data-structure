# 包含min函数的栈

## 题目 

定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））

**Tags:** Stack

## 思路1 
1. 定义两个栈stack和min，stack是正常的顺序栈，而min是辅助栈，用于存储最小值
2. push操作时，stack正常压入；node与min当前栈顶元素作比较，若node值小则直接压入min中，否则取（不弹出）当前min的栈顶元素，再次压入一个相同的值，以保持与stack压入栈的数量一致，方便pop操作

```java
class Solution { 
  
    /** 正常顺序栈 */
    private Stack<Integer> stack = new Stack<>();
    /** 最小值栈 */
    private Stack<Integer> min = new Stack<>();

    /**
     * 压栈
     * @param node
     */
    public void push(int node) {

        stack.push(node);
        // min为空直接压栈
        if (min.empty()) {
            min.push(node);
        } else {
            // node值更小，则node成为新的栈顶
            if (min.peek() > node) {
                min.push(node);
            } else {
                // 压入与当前栈顶相同值
                // 保持与压入stack数量相同，方便pop
                min.push(min.peek());
            }
        }
    }

    /**
     * 出栈（两个一起出）
     */
    public void pop() {
        stack.pop();
        min.pop();
    }

    /**
     * 返回stack的栈顶元素，但是不删除
     * @return
     */
    public int top() {
        return stack.peek();
    }

    /**
     * 返回当前栈中最小值
     * @return
     */
    public int min() {
        return min.peek();
    }

}
```
分析：

- Time complexity：O(1)