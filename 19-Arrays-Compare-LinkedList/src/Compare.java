import java.util.Random;

/**
 * @author mcrwayfun
 * @version 1.0
 * @description
 * @date Created in 2018/11/1
 */
public class Compare {

    public static void main(String[] args) {
        // 循环次数
        int totalCount = 100000;
        // 开始时间
        long startTime = System.nanoTime();
        Random random = new Random();

        Array array = new Array();
        for (int i = 0; i < totalCount; i++)
            array.addLast(random.nextInt(Integer.MAX_VALUE));

        long endTime = System.nanoTime();

        System.out.println("Array add " + totalCount / 10000 + "万次 time cost :" + (endTime - startTime) / 100000000.0);

        startTime = System.nanoTime();

        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < totalCount; i++)
            linkedList.addLast(random.nextInt(Integer.MAX_VALUE));

        endTime = System.nanoTime();

        System.out.println("LinkedList add " + totalCount / 10000 + "万次 time cost :" + (endTime - startTime) / 100000000.0);
    }
}
