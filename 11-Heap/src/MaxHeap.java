/**
 * @author mcrwayfun
 * @version 1.0
 * @description 底层使用数组来实现最大堆
 * @date Created in 2018/10/18
 */
public class MaxHeap<E extends Comparable<E>> {

    /**
     * 因为堆完全二叉树的结构，所以底层可以使用数组来实现
     * 默认根节点root下标为0
     */
    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    // 返回堆中的元素个数
    public int getSize() {
        return data.getSize();
    }

    // 判断堆是否为空
    public boolean isEmpty() {
        return data.isEmpty();
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
    private int parent(int index) {
        if (index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent");

        return (index - 1) / 2;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index) {
        return index * 2 + 2;
    }
}
