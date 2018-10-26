import java.util.ArrayList;

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

    // 获取节点的平衡因子
    private int getBalanceFactor(Node node) {
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    // 向二分搜索树中添加新的元素(key, value)
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    // 向以node为根的二分搜索树中插入元素(key, value)，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else // key.comareTo(node.key) == 0
            node.value = value;

        // 修改当前节点树的高度
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        // 调整树结构至重新平衡
        // LL：在当前节点的左子树的左子树中插入了一个节点
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0)
            rightRotate(node);
        // RR：在当前节点的右子树的右子树中插入了一个节点
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0)
            leftRotate(node);
        // LR：在当前节点的左子树的右子树中插入了一个节点
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            // 先进行一次左转，将树变为LL结构
            node.left = leftRotate(node.left);
            rightRotate(node);
        }
        // RL：在当前节点的右子树的左子树中插入了一个节点
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            // 先进行一次右转，将树变为RR结构
            node.right = rightRotate(node.right);
            leftRotate(node);
        }

        return node;
    }

    // 返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node, K key) {
        if (node == null)
            return null;

        if (key.equals(node.key))
            return node;
        else if (key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else // if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = newValue;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node) {
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node) {

        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    // 从二分搜索树中删除键为key的节点
    public V remove(K key) {

        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key) {

        if (node == null)
            return null;

        Node retNode;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else {   // key.compareTo(node.key) == 0

            // 待删除节点左子树为空的情况
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            }// 待删除节点右子树为空的情况
            else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            } else {
                // 待删除节点左右子树均不为空的情况
                // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
                // 用这个节点顶替待删除节点的位置
                Node successor = minimum(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;

                node.left = node.right = null;

                retNode = successor;
            }
        }

        if (retNode == null)
            return null;

        // 修改当前节点树的高度
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);
        // 调整树结构至重新平衡
        // LL：在当前节点的左子树的左子树中插入了一个节点
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0)
            rightRotate(retNode);
        // RR：在当前节点的右子树的右子树中插入了一个节点
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0)
            leftRotate(retNode);
        // LR：在当前节点的左子树的右子树中插入了一个节点
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            // 先进行一次左转，将树变为LL结构
            retNode.left = leftRotate(retNode.left);
            rightRotate(retNode);
        }
        // RL：在当前节点的右子树的左子树中插入了一个节点
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            // 先进行一次右转，将树变为RR结构
            retNode.right = rightRotate(retNode.right);
            leftRotate(retNode);
        }

        return retNode;
    }

    // 判断该二叉树是否是一棵二分搜索树
    public boolean isBST() {
        ArrayList<K> keys = new ArrayList<>();
        // 执行中序遍历，将全部元素放入keys中
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++)
            // 不是升序队列，直接返回false
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0)
                return false;
        return true;
    }

    // 对BST进行中序遍历，得到一个升序队列
    private void inOrder(Node node, ArrayList<K> keys) {
        if (node == null)
            return;

        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    // 判断该二叉树是否是一棵平衡二叉树
    public boolean isBalanced() {
        return isBalanced(root);
    }

    // 判断以Node为根的二叉树是否是一棵平衡二叉树，递归算法
    private boolean isBalanced(Node node) {
        if (node == null)
            return true;

        int blanceFactory = getBalanceFactor(node);
        if (Math.abs(blanceFactory) > 1)
            return false;

        return isBalanced(node.left) && isBalanced(node.right);
    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node t3 = x.right;

        x.right = y;
        y.left = t3;

        // 更新height
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));

        return x;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node t2 = x.left;

        x.left = y;
        y.right = t2;

        //更新height
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));

        return x;
    }

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            AVLTree<String, Integer> map = new AVLTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));

            System.out.println("is BST : " + map.isBST());
            System.out.println("is Balanced : " + map.isBalanced());
        }

        System.out.println();
    }
}
