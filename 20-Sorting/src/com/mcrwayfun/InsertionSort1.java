package com.mcrwayfun;

/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/11/22
 * @description 插入排序
 */
public class InsertionSort1 {

    private InsertionSort1() {
    }

    public static void sort(Comparable[] arr) {

        for (int i = 0; i < arr.length; i++) {
            Comparable e = arr[i];
            int j = i;
            for (; j > 0 && less(arr[j], arr[j - 1]); j--)
                arr[j] = arr[j - 1];

            arr[i] = e;
        }
    }

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

    private static void swap(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int N = 20000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("com.mcrwayfun.InsertionSort1", arr);
    }
}
