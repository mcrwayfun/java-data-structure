import java.util.Random;

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

    // 使用数组来构造一个最大堆，时间复杂度为O(n)
    public MaxHeap(E[] arr) {
        data = new Array<>(arr);
        // 从最后一个叶子节点的父节点开始进行siftDown操作
        for (int i = parent(getSize() - 1); i >= 0; i--)
            siftDown(i);
    }

    public MaxHeap() {
        data = new Array<>();
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

    // 向堆中添加元素
    public void add(E e) {
        // 向堆尾添加元素
        data.addLast(e);
        // 传入新增元素的下标，调整堆结构
        siftUp(data.getSize() - 1);
    }

    // 将元素向上调整
    private void siftUp(int k) {

        /**
         * 向上调整逻辑
         * k != 0,即k未调整到root节点
         * && k的父节点小于k
         */
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            // 交换数据
            data.swap(k, parent(k));
            // 交换下标
            k = parent(k);
        }
    }

    // 查看堆中的最大元素
    public E findMax() {
        if (data.getSize() == 0)
            throw new IllegalArgumentException("Can not findMax when heap is empty");
        // root节点最大，因为这是一个最大堆
        return data.get(0);
    }

    // 取出堆中的最大元素
    public E extractMax() {
        E ret = findMax();
        // 把最后一个元素移到root节点，移除最后一个节点，并执行下移操作
        // 根节点与最后一个节点元素交换
        data.swap(0, getSize() - 1);
        // 移除最后一个节点，保持堆元素个数不变
        data.removeLast();
        // 执行下移操作
        siftDown(0);

        return ret;
    }

    // 将元素向下调整
    private void siftDown(int k) {

        // 调整下标小于堆大小
        // 每次循环，父节点与左右子树做比较，将最大的调整到父节点位置
        // 使用leftChild控制循环是防止左存在而右不存在的情况
        while (leftChild(k) < getSize()) {
            // k代表父节点
            // 比较左右子树大小
            int j = leftChild(k);
            // data[j]是leftChild和rightChild中值最大的
            if (j + 1 < data.getSize() &&
                    data.get(j + 1).compareTo(data.get(j)) > 0)
                j++;// 下标交换

            // 父节点>data[j]，则循环直接结束
            if (data.get(k).compareTo(data.get(j)) >= 0)
                break;

            data.swap(k, j);
            k = j;
        }
    }

    // 取出堆中最大元素，并替换成元素e
    public E replace(E e) {

        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }

    public static void main(String[] args) {

        int n = 1000000;

        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        for (int i = 0; i < n; i++)
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));

        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = maxHeap.extractMax();

        for (int i = 1; i < n; i++)
            if (arr[i - 1] < arr[i])
                throw new IllegalArgumentException("Error");

        System.out.println("Test MaxHeap completed.");
    }
}
