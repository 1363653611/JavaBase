package com.zbcn.sort;

import com.alibaba.fastjson.JSONArray;

import javax.xml.transform.Templates;

/**
 *  快速排序
 *  <br/>
 *  @author zbcn8
 *  @since  2021/4/14 14:59
 */
public class T1_QuickSort {
    public static void main(String[] args) {
        int[] arr = {8,1,7,4,6,2,5,3};
        quickSort(arr,0, arr.length-1);
        System.out.println(JSONArray.toJSONString(arr));
    }

    private static void quickSort(int[] arr,int start, int end) {
        if (start >= end){
            return;
        }
        int left = start;
        int right = end;
        int key = arr[left];
        //当左边位置小于右边的元素时
        while (left < right){
           // 从右边开始找，找到一个比基准小的元素
            while (left < right && arr[right] >= key){
                right--;
            }
            //找到右侧比基准小的元素
            arr[left] = arr[right];
            //从左边开始找，找到第一个比基准大的元素
            while (left < right && arr[left] <= key){
                left++;
            }
            //将找到的值放到 右侧位置
            arr[right] = arr[left];
        }
        // 基准值归位
        arr[left] = key;
        //对基准左边额元素进行排序
        quickSort(arr, start, left-1);
        // 对基准右边的值进行排序
        quickSort(arr, right+1, end);
    }

}
