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
        int totalCount = 10000000;
        int[] nums = genRandomArr(totalCount);
        // 开始时间
        long startTime = System.nanoTime();

        Array array = new Array();
        for (int i = 0; i < totalCount; i++)
            array.addLast(nums[i]);

        long endTime = System.nanoTime();

        System.out.println("Array add " + totalCount / 10000 + "万次 time cost :" + (endTime - startTime) / 100000000.0);

        startTime = System.nanoTime();

        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < totalCount; i++)
            linkedList.addLast(nums[i]);

        endTime = System.nanoTime();

        System.out.println("LinkedList add " + totalCount / 10000 + "万次 time cost :" + (endTime - startTime) / 100000000.0);
    }

    private static int[] genRandomArr(int totalCount) {
        Random random = new Random();
        int[] arr = new int[totalCount];
        for (int i = 0; i < totalCount; i++)
            arr[i] = random.nextInt(Integer.MAX_VALUE);

        return arr;
    }
}
