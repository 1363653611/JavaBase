package com.zbcn.sort;

import java.util.Arrays;

/**
 * 堆排序
 * <br/>
 *
 * @author zbcn8
 * @since 2021/1/21 9:31
 */
public class HeapSort {

    static int[] arr = {8, 9, 7, 6, 5, 10, 11, 12, 13, 14};

    public static void main(String[] args) {
        //构建大顶堆
        int[] bigHeap = buildHeap(arr);
        heapSort(bigHeap);
        System.out.println("升序排列结果：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    /**
     * 堆排序：
     * 1)将已经建立好的大顶堆，每次取出根节点，即最大值。
     * 2)将最后一个节点的值赋给根节点，重新构建大顶堆。
     * 3)删除最后节点的数据
     */
    public static int[] heapSort(int[] arr) {
        for (int i = arr.length - 1; i >= 1; i--) {
            swap(arr, 0, i);
            System.out.println("交换位置后：" + Arrays.toString(arr));
            // 从根节点向下调整，每次取出一个数值，集合长度逐渐减小
            shiftDown(arr, 0, i);
            System.out.println("调整后：" + Arrays.toString(arr));
        }
        return arr;
    }

    public static int[] buildHeap(int[] arr) {
        //最后一个非叶子节点： arr.length/2-1
        int index = arr.length / 2 - 1;
        for (int i = index; i >= 0; i--) {
            shiftDown(arr, i, arr.length);
        }
        System.out.println("输出最大堆："+ Arrays.toString(arr));
        return arr;
    }

    /**
     * 调整堆结构
     *
     * @param arr
     * @param index
     * @param size  size 的值必须大于1。 如果小于等于1 则 不会改变任何结构
     */
    public static void shiftDown(int[] arr, int index, int size) {
        //当前节点的左侧孩子节点
        int leftChild = index * 2 + 1;
        // 当前节点的右侧孩子节点
        int rightChild = index * 2 + 2;
        //临时变量，存储当前索引位置
        int temp = index;
        if (leftChild < size && arr[index] < arr[leftChild]) {
            index = leftChild;
        }
        if (rightChild < size && arr[index] < arr[rightChild]) {
            index = rightChild;
        }
        if (temp != index) { //说明父节点小于孩子节点，需要调换位置
            swap(arr, index, temp);
            // 交换后继续向下调整,以index为父节点，继续向下调整
            shiftDown(arr, index, size);
        }
    }

    /**
     * 交换 arr中 索引 i 和 j 位置对应的 值
     *
     * @param arr
     * @param i
     * @param j
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
