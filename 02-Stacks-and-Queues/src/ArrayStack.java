/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/10/04
 * @description
 */
public class ArrayStack<E> implements Stack<E> {

    private Array<E> array;

    public ArrayStack(int capacity) {
        array = new Array<>(capacity);
    }

    public ArrayStack() {
        this(10);
    }

    // 获取栈中元素个数
    @Override
    public int getSize() {
        return array.getSize();
    }

    // 获取栈容量
    public int getCapacity() {
        return array.getCapacity();
    }

    // 判断栈是否为空
    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    // 将元素压入栈中
    @Override
    public void push(E e) {
        array.addLast(e);
    }

    // 出栈
    @Override
    public E pop() {
        return array.removeLast();
    }

    // 查看栈顶元素
    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append('[');
        for(int i = 0 ; i < array.getSize() ; i ++){
            res.append(array.get(i));
            if(i != array.getSize() - 1)
                res.append(", ");
        }
        res.append("] top");
        return res.toString();
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();

        for(int i = 0 ; i < 5 ; i ++){
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);
    }
}
