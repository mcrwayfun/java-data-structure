/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/10/04
 * @description
 */
public interface Queue<E> {

    int getSize();

    boolean isEmpty();

    void enqueue(E e);

    E dequeue();

    E getFront();
}
