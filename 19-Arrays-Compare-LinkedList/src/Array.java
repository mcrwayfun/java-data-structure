/**
 * @author mcrwayfun
 * @version 1.0
 * @description
 * @date Created in 2018/11/1
 */
public class Array<E> {

    private E[] data;

    private int size;

    public Array() {
        data = (E[]) new Object[10];
        this.size = 0;
    }

    // 在指定位置添加元素
    void add(int index, E e) {

        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed.index is invalid !");

        if (size == data.length)
            resize(2 * data.length);// 扩容两倍

        for (int i = size - 1; i >= index; i--)
            data[i + 1] = data[i];

        data[index] = e;
        size++;
    }

    // 删除数组中指定索引的元素
    public E remove(int index) {

        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed.index is invalid !");

        E ret = data[index];
        for (int i = index + 1; i <= size - 1; i++)
            data[i - 1] = data[i];
        size--;
        data[size] = null;// help gc

        if (size == data.length / 4 && data.length / 2 != 0)
            resize(data.length / 2);// 容量缩减为一半

        return ret;
    }

    // 向数组的末尾添加元素
    void addLast(E e) {
        add(size, e);
    }

    // 将数组容量改为newCapacity大小
    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {

        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++)
            newData[i] = data[i];

        data = newData;
    }

    // 从数组中删除元素e
    public E removeLast() {
        return remove(size - 1);
    }
}
