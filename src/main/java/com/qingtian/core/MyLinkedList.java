package com.qingtian.core;


import java.util.Collection;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.Queue;

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
     *
     * @param o
     * @return
     */
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }


    /**
     * 返回当前列表中元素的个数
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 在列表的末尾添加元素e
     *
     * @param e
     * @return
     */
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    /**
     * 移除列表中指定的且首次出现元素o
     * 若存在元素o则删除且返回true，若不存在则返回false
     *
     * @param o
     * @return
     */
    public boolean remove(Object o) {
        // 对象o值可能为null
        if (o == null) {
            // 遍历列表并删除
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            // 比较数值
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    /**
     * 在指定的下标位置插入指定的list
     *
     * @param index
     * @param c
     * @return
     */
    public boolean addAll(int index, Collection<? extends E> c) {
        // 检查下标是否越界
        checkPositionIndex(index);

        // 列表转换成数组
        Object[] a = c.toArray();
        // 列表为空则返回false
        int numNew = a.length;
        if (numNew == 0)
            return false;

        // succ表示下标index所在的结点，pred则为succ的前驱
        Node<E> pred, succ;
        // index等于size，表明在列表尾插入
        if (index == size) {
            succ = null;
            // 前驱指向尾指针
            pred = last;
        } else {
            // succ为index对应的结点
            succ = node(index);
            // pred为succ的前驱
            pred = succ.prev;
        }

        for (Object o : a) {
            @SuppressWarnings("unchecked") E e = (E) o;
            // 创建新结点newNode，其前驱指向pred
            Node<E> newNode = new Node<>(pred, e, null);
            // 无前驱则说明newNode成为头结点
            if (pred == null)
                // 头指针指向新结点
                first = newNode;
            else
                // 前驱的后继结点指向新结点
                pred.next = newNode;
            // pred移动，指向新结点
            pred = newNode;
        }

        // succ为空表明，当前插入的位置为空，即插入位置为尾结点
        // 所以尾指针指向pred，perd成为新的尾结点
        if (succ == null) {
            last = pred;
        } else {
            // pred与succ作关联
            pred.next = succ;
            succ.prev = pred;
        }

        // size增加
        size += numNew;
        // 新增结点，链表结构改变，+1
        modCount++;
        return true;
    }

    /**
     * 清空列表
     */
    public void clear() {
        // Clearing all of the links between nodes is "unnecessary", but:
        // - helps a generational GC if the discarded nodes inhabit
        //   more than one generation
        // - is sure to free memory even if there is a reachable Iterator
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
        modCount++;
    }

    /**
     * 返回指定位置的元素
     *
     * @param index 下标
     * @return
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E get(int index) {
        // 判断下标是否越界（0<=index<size）
        checkElementIndex(index);
        return node(index).item;
    }

    /**
     * 用元素e来替换指定位置index处的元素
     *
     * @param index   被替换元素的下标
     * @param element 替换的元素
     * @return 被替换的元素（原先处于index位置的）
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E set(int index, E element) {
        // 检查下标是否越界（0<=index<size）
        checkElementIndex(index);
        // 获取指定index处的元素
        Node<E> x = node(index);
        // 旧元素
        E oldVal = x.item;
        // 替换
        x.item = element;
        return oldVal;
    }

    /**
     * 在index位置插入一个新元素，原先的列表（从index处开始）
     * 向后移，所以并不会替换元素
     *
     * @param index   插入位置的下标
     * @param element 插入的元素
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public void add(int index, E element) {
        // 检查下标是否越界（0<=index<=size）
        checkPositionIndex(index);
        // 在尾部添加新元素
        if (index == size)
            linkLast(element);
        else
            // 在指定的位置添加元素
            linkBefore(element, node(index));
    }

    /**
     * 将指定的index位置元素从列表中移除，剩余的元素（index位置后的列表）
     * 向前移动
     *
     * @param index 删除的元素的下标
     * @return 删除的元素
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E remove(int index) {
        // 检查下标是否越界（0<=index<size）
        checkElementIndex(index);
        return unlink(node(index));
    }

    /**
     * 判断下标是否合理
     *
     * @param index
     */
    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * 判断index是否符合数组的下标（0<=index<size）
     */
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    /**
     * 检测下标是否合理
     *
     * @param index
     */
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * 构建IndexOutOfBoundsException错误详细信息
     *
     * @param index
     * @return
     */
    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    /**
     * 判断index是否符合位置下标，允许达到size（0<=index<=size）
     *
     * @param index
     * @return
     */
    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    /**
     * 根据index返回列表中的元素
     */
    Node<E> node(int index) {
        // assert isElementIndex(index);

        // index < size/2
        // 则从头开始找
        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            // index >= size/2
            // 从尾开始找
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
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


    // 查询操作

    /**
     * 返回o在列表中首次出现的位置
     * 若不存在则返回-1
     *
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

    /**
     * 返回指定的，且最后一次出现的元素o（下标从后往前，第一个出现的元素o）的下标
     * 若存在则返回下标，否则返回-1
     *
     * @param o 查找的元素
     * @return 下标或者-1
     */
    public int lastIndexOf(Object o) {
        int index = size;
        // 指定元素o为null值
        if (o == null) {
            // 从后往前查询
            for (Node<E> x = last; x != null; x = x.prev) {
                index--;
                if (x.item == null)
                    return index;
            }
        } else {
            // 比较数值
            for (Node<E> x = last; x != null; x = x.prev) {
                index--;
                if (o.equals(x.item))
                    return index;
            }
        }
        return -1;
    }


    // 队列操作

    /**
     * 返回列表的头元素（但不删除）
     *
     * @return 若first不为空则返回头元素，否则返回null
     * @since 1.5
     */
    public E peek() {
        // 头结点
        final Node<E> f = first;
        return (f == null) ? null : f.item;
    }

    /**
     * 返回列表的头元素（但不删除）
     *
     * @return 返回列表的头元素
     * @throws NoSuchElementException 列表为空则抛出异常
     * @since 1.5
     */
    public E element() {
        return getFirst();
    }

    /**
     * 返回列表的头元素并删除
     *
     * @return 若first不为空则返回头元素，否则返回null
     * @since 1.5
     */
    public E poll() {
        final Node<E> f = first;
        return (f == null) ? null : unlinkFirst(f);
    }

    /**
     * 返回列表的头元素并删除
     *
     * @return 列表的头元素
     * @throws NoSuchElementException 列表为空则抛出异常
     * @since 1.5
     */
    public E remove() {
        return removeFirst();
    }

    /**
     * 在列表的尾部添加元素
     *
     * @param e 添加的元素
     * @return {@code true} (as specified by {@link Queue#offer})
     * @since 1.5
     */
    public boolean offer(E e) {
        return add(e);
    }

    // 双向队列操作

    /**
     * 在列表的头部插入元素
     *
     * @param e 插入的元素
     * @return {@code true} (as specified by {@link Deque#offerFirst})
     * @since 1.6
     */
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    /**
     * 在列表的尾部插入元素
     *
     * @param e 插入的元素
     * @return {@code true} (as specified by {@link Deque#offerLast})
     * @since 1.6
     */
    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    /**
     * 返回列表的头元素（不删除）
     *
     * @return 若存在则返回头元素，不存在则返回null
     * @since 1.6
     */
    public E peekFirst() {
        final Node<E> f = first;
        return (f == null) ? null : f.item;
    }

    /**
     * 返回列表的尾元素（不删除）
     *
     * @return 若存在则返回尾元素，不存在则返回null
     * @since 1.6
     */
    public E peekLast() {
        final Node<E> l = last;
        return (l == null) ? null : l.item;
    }

    /**
     * 返回列表的头元素并且删除
     *
     * @return 若存在则返回列表头元素，不存在则返回null
     * @since 1.6
     */
    public E pollFirst() {
        final Node<E> f = first;
        return (f == null) ? null : unlinkFirst(f);
    }

    /**
     * 返回列表的尾元素并且删除
     *
     * @return 若存在则返回尾元素，不存在则返回null
     * @since 1.6
     */
    public E pollLast() {
        final Node<E> l = last;
        return (l == null) ? null : unlinkLast(l);
    }

    /**
     * 入栈：在列表的头部插入元素
     *
     * <p>This method is equivalent to {@link #addFirst}.
     *
     * @param e the element to push
     * @since 1.6
     */
    public void push(E e) {
        addFirst(e);
    }

    /**
     * 出栈：返回列表的头元素并删除
     *
     * <p>This method is equivalent to {@link #removeFirst()}.
     *
     * @return 列表的头元素
     * @throws NoSuchElementException 列表尾空则抛出异常
     * @since 1.6
     */
    public E pop() {
        return removeFirst();
    }

    /**
     * 移除列表中的指定的且第一个出现的元素o（从头到尾遍历第一个）
     * 若不存在元素o，则列表不改变
     *
     * @param o 被删除的元素
     * @return {@code true}
     * @since 1.6
     */
    public boolean removeFirstOccurrence(Object o) {
        return remove(o);
    }

    /**
     * 移除列表中的指定的且最后一个出现的元素o（从尾到头遍历第一个）
     * 若不存在元素o，则列表不改变
     *
     * @param o 被删除的元素
     * @return {@code true}
     * @since 1.6
     */
    public boolean removeLastOccurrence(Object o) {
        // o为null值
        if (o == null) {
            // 从尾到头遍历
            for (Node<E> x = last; x != null; x = x.prev) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            // 比较数值
            for (Node<E> x = last; x != null; x = x.prev) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }


}
