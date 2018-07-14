package com.qingtian.pojo;

/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/07/14
 * @description
 */
public class DoubleNode<T> {

    public T data;
    public DoubleNode left;
    public DoubleNode right;

    public DoubleNode(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
