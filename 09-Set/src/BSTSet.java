/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/10/16
 * @description
 */
public class BSTSet<E extends Comparable<E>> implements Set<E> {

    private BST bst;

    public BSTSet(){
        bst = new BST();
    }

    // 返回是否为空
    @Override
    public boolean isEmpty(){
        return bst.isEmpty();
    }

    // 返回当前元素个数
    @Override
    public int getSize(){
        return bst.getSize();
    }

    // 添加元素
    @Override
    public void add(E e){
        bst.add(e);
    }

    // 移除元素
    @Override
    public void remove(E e){
        bst.remove(e);
    }

    // 判断元素是否存在
    @Override
    public boolean contains(E e){
        return bst.contains(e);
    }
}
