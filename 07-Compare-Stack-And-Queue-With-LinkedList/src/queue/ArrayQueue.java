package queue;

/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/10/04
 * @description
 */
public class ArrayQueue<E> implements Queue<E> {

    private Array<E> array;

    public ArrayQueue(int capacity){
        array = new Array<>(capacity);
    }

    public ArrayQueue() {
        array = new Array<>();
    }

    // 获取队列中的元素个数
    @Override
    public int getSize(){
        return array.getSize();
    }

    // 获取队列的容积
    public int getCapacity(){
        return array.getCapacity();
    }

    // 判断队列是否为空
    @Override
    public boolean isEmpty(){
        return array.isEmpty();
    }

    // 入队
    @Override
    public void enqueue(E e){
        array.addLast(e);
    }

    // 出队
    @Override
    public E dequeue(){
        return array.removeFirst();
    }

    // 获取对头元素
    @Override
    public E getFront(){
        return array.getFirst();
    }

    @Override
    public String toString(){

        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
        res.append("front [");
        for(int i = 0 ; i < array.getSize() ; i ++){
            res.append(array.get(i));
            if(i != array.getSize() - 1)
                res.append(", ");
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
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
