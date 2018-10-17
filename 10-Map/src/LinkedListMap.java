import java.util.ArrayList;

/**
 * @author mcrwayfun
 * @version 1.0
 * @description
 * @date Created in 2018/10/17
 */
public class LinkedListMap<K, V> implements Map<K, V> {

    private class Node {
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key, V value) {
            this(key, value, null);
        }

        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return key.toString() + " : " + value.toString();
        }
    }

    // 虚拟头节点
    private Node dummyHead;

    private int size;

    public LinkedListMap() {
        dummyHead = new Node();
        size = 0;
    }

    // 返回映射长度
    @Override
    public int getSize() {
        return size;
    }

    // 返回映射是否为空
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // 返回key对应的node是否存在
    @Override
    public boolean contains(K key) {
        Node node = getNode(key);
        return node != null;
    }

    // 根据key获取对应的value
    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    // 设置key-value
    @Override
    public void set(K key, V newValue) {
        Node node = getNode(key);
        if (node != null)
            node.value = newValue;
    }

    // 插入新的key-value
    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if (node == null) {
            dummyHead.next = new Node(key, value, dummyHead.next);
            size++;
        } else
            node.value = value;
    }

    // 移除key对应的节点
    @Override
    public V remove(K key) {

        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.key.equals(key))
                break;
            prev = prev.next;
        }

        if (prev.next != null) {
            Node del = prev.next;
            prev.next = del.next;
            del.next = null;//help GC
            size--;
            return del.value;
        }

        return null;
    }

    // 获取key对应的node节点
    private Node getNode(K key) {

        Node cur = dummyHead.next;
        while (cur != null) {
            if (key.equals(cur.key))
                return cur;
            else
                cur = cur.next;
        }
        // 没有找到
        return null;
    }

    public static void main(String[] args){

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            LinkedListMap<String, Integer> map = new LinkedListMap<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        System.out.println();
    }
}
