/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/10/06
 * @description
 */
public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> list;

    public LinkedListStack() {
        list = new LinkedList<>();
    }

    // 返回元素个数
    @Override
    public int getSize() {
        return list.getSize();
    }

    // 返回列表是否为空
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    // 进栈
    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    // 出栈
    @Override
    public E pop() {
        return list.removeFirst();
    }

    // 查看栈顶元素
    @Override
    public E peek() {
        return list.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: top ");
        res.append(list);
        return res.toString();
    }

    public static void main(String[] args) {

        LinkedListStack<Integer> stack = new LinkedListStack<>();

        for(int i = 0 ; i < 5 ; i ++){
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);
    }
}
