package com.qingtian.source.stack;

/**
 * @author mcrwayfun
 * @version 1.0
 * @description
 * @date Created in 2018/8/2
 */
public interface Stack<E> {

    boolean isEmpty();

    void push(E data);

    E pop();

    E peek();

    void clear();

    int length();
}
