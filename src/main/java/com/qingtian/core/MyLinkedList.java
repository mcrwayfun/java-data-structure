package com.qingtian.core;


import java.util.NoSuchElementException;

/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/08/05
 * @description
 */
public class MyLinkedList<E> {

    /**
     * 链表长度
     */
    transient int size = 0;

    /**
     * 链表的头指针
     */
    transient Node<E> first;

    /**
     * 链表的尾指针
     */
    transient Node<E> last;

    /**
     * 列表结构上的改变次数
     */
    transient int modCount = 0;


    public MyLinkedList() {
    }

    /**
     * 将e作为第一个元素加入链表中
     *
     * @param e
     */
    private void linkFirst(E e) {
        final Node<E> f = first;
        // 新结点后继指向头指针
        final Node<E> newNode = new Node<>(null, e, f);
        // 头指针指向新结点
        first = newNode;
        // 原头结点为空，说明链表中无元素
        if (f == null)
            // 尾指针指向新结点
            last = newNode;
        else
            // 原头指针前驱指向新结点
            f.prev = newNode;
        size++;
        // 新加入结点，链表结构改变，+1
        modCount++;
    }

    /**
     * 在结点succ前插入一个元素e
     *
     * @param e
     * @param succ
     */
    void linkBefore(E e, Node<E> succ) {
        // assert succ != null;
        // succ的前驱结点
        final Node<E> pred = succ.prev;
        // 新结点，前驱指向pred，后继指向succ
        final Node<E> newNode = new Node<>(pred, e, succ);
        // succ的前驱指向新结点
        succ.prev = newNode;
        // 链表中仅有succ一个结点，及其前驱pred不存在
        if (pred == null)
            // 头指针指向新结点，新结点成为头结点
            first = newNode;
        else
            // pred的后即结点指向新结点
            pred.next = newNode;
        size++;
        // 新加入结点，链表结构改变，+1
        modCount++;
    }

    /**
     * 将e作为最后一个元素加入链表中
     *
     * @param e
     */
    void linkLast(E e) {
        final Node<E> l = last;
        // 新结点的前驱指向尾指针
        final Node<E> newNode = new Node<>(l, e, null);
        // 尾指针指向新结点
        last = newNode;
        // 尾指针为空，说明链表中没有元素
        if (l == null)
            // 头指针指向新结点
            first = newNode;
        else
            // 原尾指针的后继指向新结点
            l.next = newNode;
        size++;
        // 新加入结点，链表结构改变，+1
        modCount++;
    }

    /**
     * 移除链表的第一个元素f
     *
     * @param f 必须为头结点且f不能为空
     * @return
     */
    private E unlinkFirst(Node<E> f) {
        // assert f == first && f != null;
        final E element = f.item;
        // 头结点的后继
        final Node<E> next = f.next;
        f.item = null;
        // help GC
        f.next = null;
        // 头指针指向原头结点的后继
        first = next;
        // 删除后链表无元素了
        if (next == null)
            // 尾指针置空
            last = null;
        else
            // 后继的前驱置空
            next.prev = null;
        size--;
        // 删除结点，链表结构改变，+1
        modCount++;
        return element;
    }

    /**
     * 移除链表的最后一个元素
     *
     * @param l 必须为尾结点且l不能为空
     * @return
     */
    private E unlinkLast(Node<E> l) {
        // assert l == last && l != null;
        final E element = l.item;
        // 尾结点的前驱
        final Node<E> prev = l.prev;
        l.item = null;
        // help GC
        l.prev = null;
        // 尾指针指向前驱
        last = prev;
        // 删除后链表无元素了
        if (prev == null)
            // 头指针置空
            first = null;
        else
            // 前驱结点后继置空
            prev.next = null;
        size--;
        // 删除结点，链表结构改变，+1
        modCount++;
        return element;
    }

    /**
     * 删除指定的结点元素
     *
     * @param x
     * @return
     */
    E unlink(Node<E> x) {
        // assert x != null;
        final E element = x.item;
        // 删除结点的后继
        final Node<E> next = x.next;
        // 删除结点的前驱
        final Node<E> prev = x.prev;

        // TODO 2018-08-05 当链表中仅有一个元素
        if (prev == null && next == null) {
            first = last = null;
        }

        // 前驱为空，说明删除的是头结点
        if (prev == null) {
            // 头指针指向后继
            first = next;
        } else {
            // 删除结点x
            prev.next = next;
            // x结点前驱置空
            x.prev = null;
        }

        // 后继为空，说明删除的是尾结点
        if (next == null) {
            // 尾指针置空
            last = prev;
        } else {
            // 后继的前驱指向前驱
            next.prev = prev;
            // x结点的后继置空
            x.next = null;
        }


        x.item = null;
        size--;
        // 删除结点，链表结构改变，+1
        modCount++;
        return element;
    }

    /**
     * 返回链表的第一个元素
     *
     * @return
     */
    public E getFirst() {
        final Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return f.item;
    }

    /**
     * 返回链表的最后一个元素
     *
     * @return
     */
    public E getLast() {
        final Node<E> l = last;
        if (l == null)
            throw new NoSuchElementException();
        return l.item;
    }

    /**
     * 移除链表中的第一个元素
     *
     * @return
     */
    public E removeFirst() {
        final Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return unlinkFirst(f);
    }

    /**
     * 移除链表最后一个元素
     *
     * @return
     */
    public E removeLast() {
        final Node<E> l = last;
        if (l == null)
            throw new NoSuchElementException();
        return unlinkLast(l);
    }

    /**
     * 将e元素插入到链表的首位
     *
     * @param e
     */
    public void addFirst(E e) {
        linkFirst(e);
    }

    /**
     * 将e元素插入到链表的末位
     *
     * @param e
     */
    public void addLast(E e) {
        linkLast(e);
    }

    /**
     * 判断列表是是否至少包含有一个元素o（可能不止一个）
     * 若存在则返回true，否则返回false
     * @param o
     * @return
     */
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    /**
     * 返回o在列表中首次出现的位置
     * 若不存在则返回-1
     * @param o
     * @return
     */
    public int indexOf(Object o) {
        int index = 0;
        // 对象o值可能为null
        if (o == null) {
            // 遍历列表找到第一个值为null的元素
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null)
                    return index;
                index++;
            }
        } else {
            // 比较数值
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item))
                    return index;
                index++;
            }
        }
        return -1;
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
