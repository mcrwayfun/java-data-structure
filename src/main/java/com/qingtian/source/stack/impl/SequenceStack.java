package com.qingtian.source.stack.impl;

import com.qingtian.source.stack.Stack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @author mcrwayfun
 * @version 1.0
 * @description 使用数组来实现栈
 * @date Created in 2018/8/2
 */
public class SequenceStack<E> implements Stack<E> {

    /**
     * 默认长度
     */
    private static int DEFAULT_SIZE = 10;
    /**
     * 当前栈内元素
     */
    private int size = 0;
    /**
     * 数组的长度
     */
    private int capacity;
    /**
     * 保存栈内元素的数组
     */
    private Object[] elementData;

    public SequenceStack() {
        this.capacity = DEFAULT_SIZE;
        elementData = new Object[capacity];
    }

    /**
     * 指定长度初始化数组
     * @param initSize
     */
    public SequenceStack(int initSize) {
        this.capacity = initSize;
        elementData = new Object[capacity];
    }

    /**
     * 以默认的元素进栈
     *
     * @param data
     */
    public SequenceStack(E data) {
        this();
        elementData[size++] = data;
    }

    /**
     * 判断栈是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 压栈
     *
     * @param data
     * @throws IndexOutOfBoundsException
     */
    @Override
    public void push(E data) {

        // 判断栈是否已经满了
        checkIsFull();
        elementData[size++] = data;
    }

    /**
     * 出栈，并删除栈顶元素
     *
     * @return
     * @throws IndexOutOfBoundsException
     */
    @Override
    @SuppressWarnings("unchecked")
    public E pop() {

        // 判断栈是否为空
        checkIsEmpty();
        E oldValue = (E) elementData[size - 1];
        elementData[--size] = null;
        return oldValue;
    }

    /**
     * 查询栈顶元素
     *
     * @return
     * @throws IndexOutOfBoundsException
     */
    @Override
    @SuppressWarnings("unchecked")
    public E peek() {
        // 判断栈是否为空
        checkIsEmpty();
        return (E) elementData[size - 1];
    }

    /**
     * 清空栈
     */
    @Override
    public void clear() {

        Arrays.fill(elementData, null);
        size = 0;
    }

    @Override
    public int length() {
        return size;
    }

    /**
     * 若栈满了则抛出异常
     */
    private void checkIsFull() {

        if (size == capacity - 1) {
            throw new IndexOutOfBoundsException("栈已经满了");
        }
    }

    /**
     * 若栈空则抛出异常
     */
    private void checkIsEmpty() {

        if (isEmpty()) {
            throw new IndexOutOfBoundsException("栈是空的");
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder("[");
            for (int i = size - 1; i > -1; i--) {
                sb.append(elementData[i].toString() + ", ");
            }
            int len = sb.length();
            return sb.delete(len - 2, len).append("]").toString();
        }
    }

}
