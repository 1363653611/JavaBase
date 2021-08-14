package com.zbcn.algorithm;

import com.alibaba.fastjson.JSON;

import java.util.Random;

/**
 * 数组分区方式 写出来的 快速排序算法
 *
 * @author likun
 * @since 2021/8/9 9:22
 */
public class T8_Partition {

    public static void main(String[] args) {
        int[] a = {8,5,9,3,2,4,7};
//        testPart(a);
        T8_Partition partition = new T8_Partition();
        partition.quickSort(a,a.length,0, a.length-1);
        System.out.println(JSON.toJSONString(a));
    }

    private static void testPart(int[] a) {

        T8_Partition partition = new T8_Partition();
        int small = partition.partition(a, a.length, 0, a.length - 1);
        System.out.println("small = " + small);
        System.out.println(JSON.toJSONString(a));
    }

    private void  quickSort(int[] data, int length, int start, int end) {
        if (start == end){
            return;
        }
        int index = partition(data,length,start,end);
        if (index > start){
            quickSort(data,length, start, index - 1);
        }
       if (index < end){
            quickSort(data,length, index + 1, end);
        }
    }


    /**
     * 分区方法
     * partition为分割函数, length表示data数组的长度
     * @param data
     * @param length
     * @param start
     * @param end
     * @return
     */
    private int partition(int[] data, int length, int start, int end){
        if (data == null || length <= 0 || start <0 || end >= length){
            throw new IllegalArgumentException("非法的输入参数");
        }
        // 在[start, end]区间中随机取一个基准值，并将其换到区间最末尾
        int index = new Random().nextInt(end-start+1) + start;
        swap(data,index,end);
        // small在start的前面一位
        int small = start -1;
        for (index = start; index < end; ++index ){
            // 因为将基准值放到了最后一位，所以是 从 index（起始位置）开始，和基准值比较
            // 比基准值小
            if (data[index] < data[end]){
                //++small后表示大于基准值的第一个数的下标
                // 如果不存在大于基准值的数，则表示当前比较的数字下标
                ++small;
                // 交换后的small表示小于基准值的最后一个数字的下标
                if (small != index){
                    // 将 第一个 大于 基准值的 值和 小于 基准值的值交换位置。
                    swap(data, index, small);
                }
            }
        }
        // ++small后表示大于基准值的第一个数的下标
        ++small;
        swap(data,small,end);
        return small;
    }

    private void swap(int[] data,int num1, int num2) {
        int temp = data[num1];
        data[num1] = data[num2];
        data[num2] = temp;
    }
}
