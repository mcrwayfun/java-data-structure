package com.qingtian.source.queue.impl;

/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/08/05
 * @description 顺序队列测试用例
 */
public class SequenceQueueTest {

    public static void main(String[] args) {
        SequenceQueue<Integer> queue = new SequenceQueue<>();
        // 依次将元素入队
        int[] data = new int[]{1,2,3,4,5};
        initData(queue,data);
        System.out.println("----------------- 打印队列: ----------------------");
        System.out.println(queue);

        System.out.println("访问队列的front元素:" + queue.peek());
        System.out.println("移除队列的front元素:" + queue.pop());
        System.out.println("移除队列的front元素:" + queue.pop());
        System.out.println("两次调用remove方法后的队列：" + queue);
    }

    private static void initData(SequenceQueue<Integer> queue, int[] data) {

        for (int x : data) {
            queue.push(x);
        }
    }

}
