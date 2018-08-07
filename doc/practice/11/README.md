# 用两个栈实现队列

## 题目 

用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型

**Tags:** Stack

## 思路1 
1. push方法使用stack1正常入栈即可
2. pop方法：假设我们将1，2，3入队，正常的出队顺序也应该为1，2，3。先将1，2入队，此时stack1中的元素为[1,2]。若此时要出队，则应该将1弹出。而此时2是stack1的栈顶元素，故无法弹出。此时将1，2压入到stack2[2,1]。对stack2进行pop操作，则弹出1，正确出队。当我们将3入队，stack1为[3]，此时执行pop操作。因为stack2为[2]，不为空，故弹出2，正确出队。再次执行pop操作，stack2为空，将3压入stack2[3]，再执行stack2.pop()操作，弹出3，正确出队

```java
class Solution { 
  
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {

        if (stack2.empty()) {

            while (!stack1.empty()) {
                int pop = stack1.pop();
                stack2.push(pop);
            }
        }

        if (stack2.empty()) {
            // throw exception
        }

        int pop = stack2.pop();
        return pop;
    }

}
```
分析：

- Time complexity：O(1)