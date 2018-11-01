/**
 * @author mcrwayfun
 * @version 1.0
 * @description
 * @date Created in 2018/11/1
 */
public class LinkedList<E> {

    private class Node {
        public E data;
        public Node next;

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(E data) {
            this(data, null);
        }

    }

    private Node head, tail;

    private int size;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // 向链表尾部添加元素
    public void addLast(E e) {
        if (tail == null) {
            tail = new Node(e);
            head = tail;
        } else {
            Node newNode = new Node(e);
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    // 删除链表头部元素
    public E removeFirst() {
        if (size == 0)
            throw new IllegalArgumentException("Can not dequeue from an empty queue.");

        E ret = head.data;
        head = head.next;
        if (head == null)
            tail = head;
        size--;

        return ret;
    }
}
