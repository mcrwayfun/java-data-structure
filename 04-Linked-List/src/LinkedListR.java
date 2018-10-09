/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/10/09
 * @description 用递归实现链表
 */
public class LinkedListR<E> {


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

    // 虚拟结点
    private Node head;
    private int size;


    public LinkedListR() {
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

    // 递归在链表的index(0-based)位置添加新的元素e
    public void add(int index, E e) {

        if (index < 0 || index > size)
            throw new IllegalArgumentException("Can not add.index is invalid !");

        head = add(head, index, e);
        size++;
    }

    // 在链表头添加新的元素e
    public void addFirst(E e){
        add(0, e);
    }

    // 在链表末尾添加新的元素e
    public void addLast(E e){
        add(size, e);
    }

    private Node add(Node node, int index, E e) {

        if (index == 0) {
            return new Node(e, node);
        }

        node.next = add(node.next, index - 1, e);
        return node;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();

        Node cur = head;
        while(cur != null){
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL");

        return res.toString();
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++)
            res.append("--");
        return res.toString();
    }



    public static void main(String[] args) {
        LinkedListR<Integer> list = new LinkedListR<>();
        for(int i = 0 ; i < 2 ; i ++)
            list.addFirst(i);
        System.out.println(list);

        list.add(1,111);
        System.out.println(list);
    }
}

