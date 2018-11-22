package com.mcrwayfun;

import java.util.Arrays;

/**
 * @author mcrwayfun
 * @version v1.0
 * @date Created in 2018/11/22
 * @description 选择排序
 */
public class SelectionSort {

    private SelectionSort() {
    }

    public static void sort(Comparable[] arr) {

        for (int i = 0; i < arr.length; i++) {
            // 当前i表示最小值
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++)
                if (less(arr[j], arr[minIndex]))
                    minIndex = j;
            swap(arr, i, minIndex);
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
        SortTestHelper.testSort("com.mcrwayfun.SelectionSort", arr);
    }
}
