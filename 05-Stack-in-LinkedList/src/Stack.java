/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/10/04
 * @description stack 接口
 */
public interface Stack<E> {

    int getSize();

    boolean isEmpty();

    void push(E e);

    E pop();

    E peek();
}
