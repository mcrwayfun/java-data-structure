package com.qingtian.pojo;


/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/07/14
 * @description
 */
public class Node<T> {


    public T data;
    public Node next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
