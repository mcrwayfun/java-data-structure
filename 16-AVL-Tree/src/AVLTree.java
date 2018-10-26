/**
 * @author mcrwayfun
 * @version 1.0
 * @description
 * @date Created in 2018/10/26
 */
public class AVLTree<K extends Comparable<K>, V> {

    private class Node {
        public K key;
        public V value;
        public Node left, right;
        // 当前节点的高度
        public int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    // 返回当前AVL树长度
    public int getSize() {
        return size;
    }

    // 返回当前树是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 获取节点node的高度
    private int getHeight(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

}
