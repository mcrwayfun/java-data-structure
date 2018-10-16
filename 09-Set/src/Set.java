/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/10/16
 * @description
 */
public interface Set<E> {

    boolean isEmpty();

    int getSize();

    void add(E e);

    void remove(E e);

    boolean contains(E e);
}
