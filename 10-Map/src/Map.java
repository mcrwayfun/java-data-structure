/**
 * @author mcrwayfun
 * @version 1.0
 * @description
 * @date Created in 2018/10/17
 */
public interface Map<K,V> {

    boolean isEmpty();

    int getSize();

    boolean contains(K key);

    void add(K key,V value);

    V remove(K key);

    void set(K key,V value);

    V get(K key);
}
