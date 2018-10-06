package queue;

/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/10/06
 * @description 双向链表实现队列
 */
public class LinkedListQueue<E> implements Queue<E> {

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

    private Node head, tail;
    private int size;

    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    // 返回列表中元素个数
    @Override
    public int getSize() {
        return size;
    }

    // 返回列表是否为空
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // 入队
    @Override
    public void enqueue(E e) {

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

    // 出队
    @Override
    public E dequeue(){

        if(isEmpty())
            throw new IllegalArgumentException("Can not dequeue from an empty queue.");

        Node ret = head;
        head = head.next;
        if(head == null)
            tail = null;
        size--;

        return ret.e;
    }

    // 查看队头元素
    @Override
    public E getFront(){

        if(isEmpty())
            throw new IllegalArgumentException("Can not dequeue from an empty queue.");

        return head.e;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Queue: front ");

        Node cur = head;
        while(cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL tail");
        return res.toString();
    }

    public static void main(String[] args){

        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);

            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
