package com.zbcn.algorithm;

import com.alibaba.fastjson.JSON;

/**
 * 数组排序：
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有偶数位于数组的后半部分
 *
 * @author likun
 * @since 2021/8/17 17:43
 */
public class T13_ArraySort {

    public static void main(String[] args) {
        int[] arr = {1,3,4,5,6,7};
        arraySort(arr);
        System.out.println(JSON.toJSONString(arr));
    }

    private static void arraySort(int[] arr) {
        if (arr == null){
            return;
        }
        //第一个指针指向开始
        int preIndex = 0;
        //第二个指针指向数组的尾部位置
        int tailIndex = arr.length - 1;
        while (preIndex < tailIndex){
            //向后移动 preIndex ，直到他指向 偶数位
            while (preIndex < tailIndex && (arr[preIndex] & 0x1) != 0 ){
                preIndex++;
            }
            // 向前移动 tailIndex， 直到 他指向奇数位
            while (preIndex < tailIndex && (arr[tailIndex] & 0x1) == 0){
                tailIndex--;
            }
            if (preIndex < tailIndex){
                int temp = arr[preIndex];
                arr[preIndex] = arr[tailIndex];
                arr[tailIndex] = temp;
            }
        }

    }
}
