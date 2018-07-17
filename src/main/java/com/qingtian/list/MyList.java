package com.qingtian.list;

/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/07/16
 * @description
 */
public interface MyList<E> {

    void clear();

    boolean isEmpty();

    void add(int index,E e);

    void add(E e);

    void remove(int index);

    E get(int index);

}
