/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/10/05
 * @description
 */
public class LinkedList<E> {

    private class Node {

        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    // 头结点
    private Node head;
    private int size;


    public LinkedList() {
        head = null;
        size = 0;
    }

    // 返回链表中的元素个数
    public int getSize() {
        return size;
    }

    // 返回链表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 在链表头添加元素
    public void addFirst(E e) {

        head = new Node(e, head);
        size++;
    }

    // 在链表的index(0-based)位置添加新的元素e
    public void add(int index, E e) {

        if (index < 0 || index > size)
            throw new IllegalArgumentException("Cannot add.index is invalid !");

        // index is 0
        if (index == 0)
            addFirst(e);
        // find the node before index
        Node prev = head;
        for (int i = 0; i < index - 1; i++)
            prev = prev.next;

        prev.next = new Node(e, prev.next);
        size++;
    }
        
    // 在链表末尾添加元素e
    public void addLast(E e) {
        add(size, e);
    }
}
