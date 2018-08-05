package com.qingtian.source.queue;

/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/08/04
 * @description
 */
public interface Queue<E> {

    boolean isEmpty();

    void push(E data);

    E pop();

    E peek();

    void clear();

    int length();

}
