package com.qingtian.source.queue.impl;

/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/08/05
 * @description
 */
public class LoopQueueTest {

    public static void main(String[] args) {
        LoopQueue<Integer> queue
                = new LoopQueue<>(1,3);
        // 添加两个元素
        queue.push(2);
        queue.push(3);
        // 此时队列已满
        System.out.println(queue);
        // 删除一个元素后，队列可以再多加一个元素
        queue.pop();
        System.out.println("删除一个元素后的队列：" + queue);
        // 再次添加一个元素，此时队列又满
        queue.push(4);
        System.out.println(queue);
        System.out.println("队列满时的长度：" + queue.length());
        // 查询front元素
        System.out.println("查询front元素:" + queue.peek());
    }

}
