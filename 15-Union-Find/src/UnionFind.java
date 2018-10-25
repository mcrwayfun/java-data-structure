/**
 * @author mcrwayfun
 * @version 1.0
 * @description
 * @date Created in 2018/10/25
 */
public class UnionFind {


    // rank[i]表示以i为根的集合所表示的树的层数
    // 在后续的代码中, 我们并不会维护rank的语意, 也就是rank的值在路径压缩的过程中, 有可能不再是树的层数值
    // 这也是我们的rank不叫height或者depth的原因, 它只是作为比较的一个标准
    private int[] rank;
    private int[] parent; // parent[i]表示第i个元素所指向的父节点
}
