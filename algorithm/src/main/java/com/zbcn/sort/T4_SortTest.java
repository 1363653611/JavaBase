package com.zbcn.sort;

import java.util.Arrays;

/**
 * 数据结构与算法中的 排序问题联系
 *
 * @author likun
 * @since 2022/9/19 13:32
 */
public class T4_SortTest {

    public static void main(String[] args) {
        int[] a = {4, 5, 6, 3, 2, 1};
        //bubbleSort(a);
        //insertSort(a);
        //selectSort(a);
        //System.out.println(Arrays.toString(a));
        int[] result = mergeSort(a);

        System.out.println(Arrays.toString(result));
    }

    /**
     * 冒泡排序
     *
     * @param a
     */
    public static void bubbleSort(int[] a) {
        if (a == null || a.length < 0) {
            return;
        }

        // 外层循环，控制冒泡次数
        for (int i = 0; i < a.length; i++) {
            // 是否有元素交换
            boolean flag = false;
            for (int j = 0; j < a.length - 1; j++) {
                // 判断两个元素大小
                if (a[j] > a[j + 1]) {
                    // 交换元素
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                // 如果没有数据交换，则提前退出
                break;
            }
        }

    }

    /**
     * 插入排序
     *
     * @param a
     */
    public static void insertSort(int[] a) {

        if (a != null || a.length == 0) {
            return;
        }
        // 初始已排序区间（只有一个元素 a[0]）和未排序区间，
        for (int i = 1; i < a.length; i++) {
            // 未排序区间中的第一个元素
            int value = a[i];
            // 已排序区间的最后 一个元素
            int j = i - 1;
            // 遍历已排序区间，找出 value 的插入位置，（遍历过程中，将比 value 大的元素顺便后移，以便给 value腾出插入位置）
            for (; j >= 0; j--) {
                if (value < a[j]) {
                    // 将元素位置移动
                    a[j + 1] = a[j];
                } else {
                    // 顺序合适，位置不用移动
                    break;
                }
            }
            a[j + 1] = value;
        }

    }


    /**
     * 选择排序
     *
     * @param a
     */
    public static void selectSort(int[] a) {
        if (a == null || a.length == 0) {
            return;
        }
        // 控制循环次数
        for (int i = 0; i < a.length; i++) {
            // 找出最小元素的索引
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            // 将当前元素与最小元素交换位置
            if (minIndex != i) {
                // 最小值 互换
                int temp = a[i];
                a[i] = a[minIndex];
                a[minIndex] = temp;
            }
        }
    }


    /**
     * 并归排序
     *
     * @param a
     */
    public static int[] mergeSort(int[] a) {

        // 分而治之 的实现方式 ---》 递归算法
        // 递推公式  doMergeSort(int[] a, start, end) = merge(doMergeSort（a，start, q），doMergeSort（a，q+1, end）)
        // 递归结束条件： start >= r; 不能再继续拆解了
        a = doMergeSort(a, 0, a.length - 1);
        return a;

    }

    private static int[] doMergeSort(int[] a, int start, int end) {

        if (end - start < 1) {
            int[] result = new int[end - start + 1];
            int index = 0;
            while (start <= end) {
                result[index++] = a[start];
                start++;
            }
            return result;
        }

        int mid = (start + end) / 2;
        // 前半部分排序
        int[] pre = doMergeSort(a, start, mid);
        // 后半部分排序
        int[] secondHarf = doMergeSort(a, mid + 1, end);

        // 将排好序的数组合并
        int[] result = new int[pre.length + secondHarf.length];
        merge(pre, secondHarf, result);
        return result;
    }

    private static void merge(int[] pre, int[] secondHarf, int[] result) {
        int i = 0;
        int j = 0;
        int q = 0;
        while (i < pre.length && j < secondHarf.length) {
            int first = pre[i];
            int second = secondHarf[j];
            if (first < second) {
                result[q] = first;
                i++;
            } else {
                result[q] = second;
                j++;
            }
            q++;
        }
        // 合并剩余元素
        while (i <= pre.length - 1) {
            result[q++] = pre[i++];
        }
        while (j <= secondHarf.length - 1) {
            result[q++] = secondHarf[j++];
        }
    }

    /**
     * 快排算法
     * 递推公式： quickSort(p....r) = quickSort(p... q-1) + quickSort(q+1, r)
     * 终止条件: p >= r
     * @param a
     */
    public static void quickSort(int[] a){
            doQuickSort(a, 0,a.length-1);
    }

    /**
     * 快排，递归函数方式
     * @param a
     * @param start
     * @param end
     */
    private static void doQuickSort(int[] a, int start, int end) {
        if (start >= end){
            return;
        }
        int q = partition(a,start,end);
        doQuickSort(a,start,q - 1);
        doQuickSort(a,q + 1, end);
    }

    /**
     * 具体的分区方法，获取分区点. 设计非常巧妙 --- 原地分区
     * @param a
     * @param start
     * @param end
     * @return
     */
    private static int partition(int[] a, int start, int end) {

        // 获取分区点的值
        int pivot = a[end];
        //游标 i 用来区分 已处理区间（比 pivot 小的值都放在已处理区间）和未处理区间
        int i = start;
        // 从未处理区间
        for ( int j = start; j <= end; j++){
            if (a[j] < pivot){
                // i 和 j 对应的元素互换位置
                swap(a,i,j);
                i++;
            }
        }
        swap(a,i,end);
        return i;
    }

    /**
     * 将 数组中指定两个位置的元素互换位置
     * @param a
     * @param i
     * @param j
     */
    private static void swap(int[] a, int i, int j) {
        if (i != j){
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }

}
