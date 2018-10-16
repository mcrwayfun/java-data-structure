import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/10/10
 * @description 二叉搜索树
 */
public class BST<E extends Comparable<E>> {

    private class Node {
        public E e;
        public Node left;
        public Node right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    // 根结点
    private Node root;

    private int size;

    // 返回树中总结点数
    public int getSize() {
        return size;
    }

    // 返回树是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 向二分搜索树中添加新的元素e
    public void add(E e) {
        root = add(root, e);
    }

    // 向以node为根的二分搜索树中插入元素e，递归算法
    // 返回插入新结点后二分搜索树的根
    private Node add(Node node, E e) {

        if (node == null) {
            size++;
            return new Node(e);
        }

        // e < node.e
        if (e.compareTo(node.e) < 0)
            node.left = add(node.left, e);
            // e > node.e
        else if (e.compareTo(node.e) > 0)
            node.right = add(node.right, e);

        return node;
    }

    // 看二分搜索树中是否包含元素e
    public boolean contains(E e) {
        return contains(root, e);
    }

    // 看以node为根的二分搜索树中是否包含元素e, 递归算法
    private boolean contains(Node node, E e) {

        // can not find the target node
        if (node == null)
            return false;

        if (e.compareTo(node.e) == 0)
            return true;
        else if (e.compareTo(node.e) < 0)
            return contains(node.left, e);
        else
            return contains(node.right, e);
    }

    // 二分搜索树的前序遍历
    public void preOrder() {
        preOrder(root);
    }

    // 前序遍历以node为根的二分搜索树, 递归算法
    private void preOrder(Node node) {

        if (node == null)
            return;

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    // 二分搜索树的中序遍历
    public void inOrder() {
        inOrder(root);
    }

    // 中序遍历以node为根的二分搜索树, 递归算法
    private void inOrder(Node node) {
        if (node == null)
            return;

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    // 二分搜索树的后序遍历
    public void postOrder() {
        postOrder(root);
    }

    // 后序遍历以node为根的二分搜索树, 递归算法
    private void postOrder(Node node) {
        if (node == null)
            return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    // 二分搜索树的层序遍历
    public void levelOrder() {

        if (root == null)
            return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {

            Node cur = queue.remove();
            System.out.println(cur.e);

            if (root.left != null)
                queue.add(root.left);
            if (root.right != null)
                queue.add(root.right);
        }
    }

    // 寻找二分搜索树的最小元素
    public E minimum() {

        if (size == 0)
            throw new IllegalArgumentException("Search Min Value Failed! BST is empty");

        Node minNode = minimum(root);
        return minNode.e;
    }

    // 返回以node为根的二分搜索树的最小值所在的结点
    private Node minimum(Node node) {

        if (node.left == null)
            return node;

        return minimum(node.left);
    }

    // 寻找二分搜索树的最大元素
    public E maximum() {

        if (size == 0)
            throw new IllegalArgumentException("Search Max Value Failed! BST is empty");

        Node maxNode = maximum(root);
        return maxNode.e;
    }

    private Node maximum(Node node) {

        if (node.right == null)
            return node;

        return minimum(node.right);
    }

    // 从二分搜索树中删除最小值所在结点, 返回最小值
    public E removeMin() {

        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    // 删除掉以node为根的二分搜索树中的最小结点
    // 返回删除结点后新的二分搜索树的根
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

    // 从二分搜索树中删除最大值所在结点，返回最大值
    public E removeMax() {

        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    // 删除以node为根的二分搜索树中的最大结点
    // 返回删除结点新的二分搜索树的根
    private Node removeMax(Node node) {

        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    // 从二分搜索树中删除元素为e的节点
    public void remove(E e) {
        root = remove(root, e);
    }

    // 删除掉以node为根的二分搜索树中值为e的节点, 递归算法
    // 返回删除节点后新的二分搜索树的根
    private Node remove(Node node, E e) {

        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        }
        if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else {
            // e.compareTo(node.e) == 0

            // 左子树为空
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            // 右子树为空
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // 待删除节点左右子树均不为空的情况

            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    // 生成以node为根结点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node, int depth, StringBuilder res) {

        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++)
            res.append("--");
        return res.toString();
    }

    // 打乱数组顺序
    private static void shuffle(Object[] arr){

        for(int i = arr.length - 1 ; i >= 0 ; i --){
            int pos = (int) (Math.random() * (i + 1));
            Object t = arr[pos];
            arr[pos] = arr[i];
            arr[i] = t;
        }
    }

    public static void main(String[] args) {
//        BST<Integer> bst = new BST<>();
//        int[] nums = {5, 3, 6, 8, 4, 2};
//        for (int num : nums)
//            bst.add(num);
//
//        /////////////////
//        //      5      //
//        //    /   \    //
//        //   3    6    //
//        //  / \    \   //
//        // 2  4     8  //
//        /////////////////
//        bst.preOrder();
//        System.out.println();
//
//        bst.inOrder();
//        System.out.println();
//
//        bst.postOrder();
//        System.out.println();

//        System.out.println(bst);

        BST<Integer> bst = new BST<>();
        Random random = new Random();

        int n = 10000;

        for(int i = 0 ; i < n ; i ++)
            bst.add(random.nextInt(n));

        // 注意, 由于随机生成的数据有重复, 所以bst中的数据数量大概率是小于n的

        // order数组中存放[0...n)的所有元素
        Integer[] order = new Integer[n];
        for( int i = 0 ; i < n ; i ++ )
            order[i] = i;
        // 打乱order数组的顺序
        shuffle(order);

        // 乱序删除[0...n)范围里的所有元素
        for( int i = 0 ; i < n ; i ++ )
            if(bst.contains(order[i])){
                bst.remove(order[i]);
                System.out.println("After remove " + order[i] + ", size = " + bst.getSize() );
            }

        // 最终整个二分搜索树应该为空
        System.out.println(bst.getSize());
        System.out.println(Arrays.toString(order));
    }
}
