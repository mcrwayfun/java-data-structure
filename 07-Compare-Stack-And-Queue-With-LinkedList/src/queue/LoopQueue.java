package queue;

/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/10/04
 * @description
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int size;
    private int front, tail;

    @SuppressWarnings("unchecked")
    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
        size = 0;
        front = 0;
        tail = 0;
    }

    public LoopQueue() {
        this(10);
    }

    // 获取队列中的元素个数
    @Override
    public int getSize() {
        return size;
    }

    // 获取队列的容积
    public int getCapacity() {
        return data.length - 1;
    }

    // 判断队列是否为空
    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    // 入队
    @Override
    public void enqueue(E e) {

        // 判断队列是否满了
        if ((tail + 1) % data.length == front)
            resize(getCapacity() * 2);

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    // 出队
    @Override
    public E dequeue() {

        if (isEmpty())
            throw new IllegalArgumentException("Can not dequeue from empty queue !");

        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;

        if (size == getCapacity() / 4 && getCapacity() / 2 != 0)
            resize(getCapacity() / 2);

        return ret;
    }

    // 获取队头元素
    @Override
    public E getFront() {

        if (isEmpty())
            throw new IllegalArgumentException("Can not dequeue from empty queue !");

        return data[front];
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d\n", size, getCapacity()));
        res.append("front [");
        for (int i = front; i != tail; i = (i + 1) % data.length){
            res.append(data[i]);
            if((i+1) % data.length != tail)
                res.append(", ");
        }
        res.append("] tail");

        return res.toString();
    }

    // 将数组容量改变为newCapacity
    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {

        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++)
            newData[i] = data[(i + front) % data.length];

        data = newData;
        front = 0;
        tail = size;
    }

    public static void main(String[] args) {

        LoopQueue<Integer> queue = new LoopQueue<>();
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);

            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
