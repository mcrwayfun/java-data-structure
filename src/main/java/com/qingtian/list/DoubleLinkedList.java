package com.qingtian.list;

import com.qingtian.pojo.DoubleNode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/07/14
 * @description
 */
public class DoubleLinkedList<T> {

    public DoubleNode first;

    public DoubleLinkedList() {
        first = new DoubleNode<>("A");
        first.left = null;
        first.right = null;
    }

    /**
     * 新建一个链表
     */
    public void createList() {

        if (first == null) {
            first = new DoubleNode<>("A");
            first.left = null;
            first.right = null;
        }

        DoubleNode b = new DoubleNode<>("B");
        DoubleNode c = new DoubleNode<>("B");
        DoubleNode d = new DoubleNode<>("B");
        DoubleNode e = new DoubleNode<>("B");

        first.right = b;
        b.left = first;

        b.right = c;
        c.left = b;

        c.right = d;
        d.left = c;

        d.right = e;
        e.left = d;
    }

    /**
     * 判断链表是否为空
     *
     * @return 空则返回true
     * @author mcrwayfun
     * @version 1.0
     * @date 2018/7/14
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * 清空一个链表
     *
     * @author mcrwayfun
     * @version 1.0
     * @date 2018/7/14
     */
    public void clear() {
        this.first = null;
    }


    /**
     * 输出一个链表
     *
     * @author mcrwayfun
     * @version 1.0
     * @date 2018/7/14
     */
    public void print(){
        DoubleNode current = first;
        while(current != null){
            System.out.print(current.data + " ");
            current = current.right;
        }
        System.out.println();
    }

    /**
     * 在指定的节点位置后插入一个节点
     *
     * @param node
     * @param data
     */
    public void insert(DoubleNode node,String data){

        DoubleNode insNode = new DoubleNode<>("data");

        // 链表尾空
        if(this.isEmpty()){
            first = insNode;
        }else {
            // 链表头插入
            if(first.data.equals(node.data)){
                List list = new LinkedList();
            }
        }
    }
}
