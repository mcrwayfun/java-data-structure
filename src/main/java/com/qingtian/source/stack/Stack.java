package com.qingtian.source.stack;

/**
 * @author mcrwayfun
 * @version 1.0
 * @description
 * @date Created in 2018/8/2
 */
public interface Stack {

    boolean isEmpty();

    void push(int data);

    int pop();

    int peek();

    void clear();

    void print();
}
