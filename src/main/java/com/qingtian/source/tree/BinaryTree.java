package com.qingtian.source.tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @Author mcrwayfun
 * @Date Created in 2018/07/01
 * @Version v1.0
 * @Description
 */
public class BinaryTree {

    private static final Logger log = LoggerFactory.getLogger(BinaryTree.class);

    /**
     * 根结点
     */
    private TreeNode root = null;

    /**
     * 构造器（初始化根结点）
     */
    public BinaryTree() {
        root = new TreeNode(1, "A");
    }

    /**
     * 构建二叉树
     *         A
     *       /  \
     *      B    C
     *     / \  /
     *    D  E F
     */
    public void createBinaryTree() {
        TreeNode nodeB = new TreeNode(2, "B");
        TreeNode nodeC = new TreeNode(3, "C");
        TreeNode nodeD = new TreeNode(4, "D");
        TreeNode nodeE = new TreeNode(5, "E");
        TreeNode nodeF = new TreeNode(6, "F");
        root.leftChild = nodeB;
        root.rightChild = nodeC;
        nodeB.leftChild = nodeD;
        nodeB.rightChild = nodeE;
        nodeC.leftChild = nodeF;
    }

    /**
     * 求二叉树的深度（高度）
     *
     * @return
     */
    public int getHeight() {
        return getHeight(root);
    }

    /**
     * 求二叉树的深度（高度）
     *
     * @param root
     * @return
     */
    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int i = getHeight(root.leftChild);
            int j = getHeight(root.rightChild);
            return i >= j ? i + 1 : j + 1;
        }
    }

    /**
     * 求二叉树的结点数
     *
     * @return
     */
    public int getSize() {
        return getSize(root);
    }

    /**
     * 求二叉树的结点数
     *
     * @param root
     * @return
     */
    private int getSize(TreeNode root) {

        if (root == null) {
            return 0;
        } else {
            return 1 + getSize(root.leftChild) + getSize(root.rightChild);
        }
    }

    /**
     * 前序遍历（中左右）
     * output:A-B-D-E-C-F
     *
     * @param root
     */
    public void preOrder(TreeNode root) {

        if (root == null) {
            return;
        } else {
            log.info("preOrder data:" + root.getData());
            preOrder(root.leftChild);
            preOrder(root.rightChild);
        }
    }

    /**
     * 中序遍历（左中右）
     * output:D-B-E-A-F-C
     *
     * @param root
     */
    public void midOrder(TreeNode root) {

        if (root == null) {
            return;
        } else {
            midOrder(root.leftChild);
            log.info("midOrder data:" + root.getData());
            midOrder(root.rightChild);
        }
    }

    /**
     * 后序遍历（左右中）
     * output:D-E-B-F-C-A
     *
     * @param root
     */
    public void postOrder(TreeNode root) {

        if (root == null) {
            return;
        } else {
            postOrder(root.leftChild);
            postOrder(root.rightChild);
            log.info("postOrder data:" + root.getData());
        }
    }

    /**
     * 前序遍历非递归算法
     * output:A-B-D-E-C-F
     *
     * @param root
     */
    public void preOrderNonRecursive(TreeNode root) {

        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(null);
        while (root != null) {
            // 访问根结点
            log.info("preOrderNonRecursive data:" + root.getData());
            // 当前结点右子树不为空则放入栈中
            if (root.rightChild != null)
                nodeStack.push(root.rightChild);
            // 访问左子树
            if (root.leftChild != null)
                root = root.leftChild;
            else root = nodeStack.pop();
        }
    }

    /**
     * 中序遍历非递归算法
     * output:D-B-E-A-F-C
     *
     * @param root
     */
    public void midOrderNonRecursive(TreeNode root) {

        Stack<TreeNode> nodeStack = new Stack<>();
        do {
            while (root != null) {
                nodeStack.push(root);
                root = root.leftChild;
            }

            if (!nodeStack.empty()) {
                root = nodeStack.pop();
                log.info("preOrderNonRecursive data:" + root.getData());
                root = root.rightChild;
            }
        } while (root != null || !nodeStack.empty());
    }

    /**
     * 后序遍历非递归算法
     * output:D-E-B-F-C-A
     *
     * @param root
     */
    public void postOrderNonRecursive(TreeNode root) {

        Stack<TreeNode> nodeStack = new Stack<>();
        // 上一个结点
        TreeNode prev = root;
        do {
            while (root != null) {
                nodeStack.push(root);
                root = root.leftChild;
            }

            // 访问当前结点的右结点
            if (!nodeStack.empty()) {
                // 获取右子树，但先不弹出
                TreeNode temp = nodeStack.peek().rightChild;
                // 不存在右子树或右子树已经访问过，可以访问父结点
                if (temp == null || temp == prev) {

                    root = nodeStack.pop();
                    log.info("postOrderNonRecursive data:" + root.getData());
                    // 记录访问过的结点
                    prev = root;
                    // 当前结点置空
                    root = null;
                } else {
                    // 存在右子树，需要优先访问右子树
                    root = temp;
                }
            }
        } while (root != null || !nodeStack.empty());
    }

    /**
     * 层序遍历
     *
     * @param root
     */
    public List<List<String>> levelOrder(TreeNode root) {

        List<List<String>> reList = new ArrayList<>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        // 压入根结点
        nodeQueue.offer(root);
        while (!nodeQueue.isEmpty()) {
            int levelSize = nodeQueue.size();
            List<String> subList = new ArrayList<>();
            while (levelSize != 0) {
                TreeNode temp = nodeQueue.poll();
                subList.add(temp.getData() + "");
                if (temp.leftChild != null) nodeQueue.offer(temp.leftChild);
                if (temp.rightChild != null) nodeQueue.offer(temp.rightChild);
                levelSize--;
            }
            reList.add(subList);
        }

        return reList;
    }

    /**
     * 复制一棵二叉树
     *
     * @param parent
     * @return
     */
    public TreeNode copy(TreeNode parent) {

        if (parent == null)
            return null;

        // 构造根结点
        TreeNode temp = new TreeNode(parent.getIndex(), parent.getData());

        // 递归构造左子树
        temp.leftChild = copy(parent.leftChild);

        // 递归构造右子树
        temp.rightChild = copy(parent.rightChild);

        return temp;
    }

    /**
     * 判断两棵树是否相等
     *
     * @param s
     * @param t
     * @return
     */
    public boolean euqal(TreeNode s, TreeNode t) {

        if (s == null && t == null)
            return true;

        if (s != null && t != null && s.data == t.data &&
                euqal(s.leftChild, t.leftChild) &&
                euqal(s.rightChild, t.rightChild))
            return true;
        else
            return false;
    }

    /**
     * 前序遍历创建二叉树
     * ABD##E##CF###
     *
     * @param data
     * @return
     */
    public TreeNode createBinaryTree(List<String> data) {

        if (data == null || data.isEmpty())
            return null;

        return createBinaryTreeHelper(data.size(), data);
    }

    private TreeNode createBinaryTreeHelper(int size, List<String> data) {

        TreeNode node;

        String d = data.get(0);
        int index = size - data.size();

        // 遇到结束符#
        if (d.equals("#")) {
            node = null;
            data.remove(0);
            return node;
        }
        node = new TreeNode(index, d);
        // 根结点
        if (index == 0) {
            root = node;
        }
        data.remove(0);
        node.leftChild = createBinaryTreeHelper(size, data);
        node.rightChild = createBinaryTreeHelper(size, data);
        return node;

    }

    /**
     * 构造函数
     *
     * @param <T>
     */
    public class TreeNode<T> {
        private int index;
        private T data;
        private TreeNode leftChild;
        private TreeNode rightChild;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public TreeNode(int index, T data) {
            this.index = index;
            this.data = data;
            this.leftChild = null;
            this.rightChild = null;
        }
    }

    public static void main(String[] args) {

        BinaryTree binaryTree = new BinaryTree();
        binaryTree.createBinaryTree();

        /*----------------- 二叉树高度 ----------------------*/
        log.info("----------------- 二叉树高度 ----------------------");
        int height = binaryTree.getHeight();
        log.info("treeHeihgt:" + height);

        /*----------------- 二叉树结点 ----------------------*/
        log.info("----------------- 二叉树结点 ----------------------");
        int size = binaryTree.getSize();
        log.info("treeSize:" + size);

        /*----------------- 二叉树前序遍历 ----------------------*/
        log.info("----------------- 二叉树前序遍历 ----------------------");
        // A-B-D-E-C-F
        binaryTree.preOrder(binaryTree.root);

        /*----------------- 二叉树中序遍历 ----------------------*/
        log.info("----------------- 二叉树中序遍历 ----------------------");
        // D-B-E-A-F-C
        binaryTree.midOrder(binaryTree.root);

        /*----------------- 二叉树后序遍历 ----------------------*/
        log.info("----------------- 二叉树后序遍历 ----------------------");
        // D-E-B-F-C-A
        binaryTree.postOrder(binaryTree.root);

        /*----------------- 复制一棵二叉树 ----------------------*/
        log.info("----------------- 复制一棵二叉树 ----------------------");
        // copy
        TreeNode copy = binaryTree.copy(binaryTree.root);
        // A-B-D-E-C-F
        binaryTree.preOrder(copy);

        /*----------------- 判断两棵二叉树是否相等 ----------------------*/
        log.info("----------------- 判断两棵二叉树是否相等 ----------------------");
        // 判断两棵树是否相等
        boolean euqal = binaryTree.euqal(binaryTree.root, copy);
        log.info("两棵树是否相等:" + euqal);

        /*----------------- 利用前序遍历构建二叉树 ----------------------*/
        log.info("----------------- 利用前序遍历构建二叉树 ----------------------");
        String[] strs = new String[]{"A", "B", "D", "#", "#", "E", "#", "#", "C", "F", "#", "#", "#"};

        List<String> data = new ArrayList<>();
        for (String s : strs) {
            data.add(s);
        }

        TreeNode createBinaryTree = binaryTree.createBinaryTree(data);
        // A-B-D-E-C-F
        binaryTree.preOrder(createBinaryTree);

        /*----------------- 非递归前序遍历 ----------------------*/
        // A-B-D-E-C-F
        log.info("----------------- 非递归前序遍历 ----------------------");
        binaryTree.preOrderNonRecursive(binaryTree.root);

        /*----------------- 非递归中序遍历 ----------------------*/
        // D-B-E-A-F-C
        log.info("----------------- 非递归中序遍历 ----------------------");
        binaryTree.midOrderNonRecursive(binaryTree.root);


        /*----------------- 非递归后序遍历 ----------------------*/
        // D-E-B-F-C-A
        log.info("----------------- 非递归后序遍历 ----------------------");
        binaryTree.postOrderNonRecursive(binaryTree.root);

        /*----------------- 层序遍历 ----------------------*/
        log.info("----------------- 层序遍历 ----------------------");
        List<List<String>> levelOrder = binaryTree.levelOrder(binaryTree.root);
        for (List<String> list : levelOrder) {
            log.info(list.toString());
        }

    }

}
