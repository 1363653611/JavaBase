package com.zbcn.sort;

import com.alibaba.fastjson.JSONArray;

public class T1_BubbleSort {

    public static void main(String[] args) {
        int[] arr = {1,7,4,6,2,5,3};
        bubbleSort(arr);
        System.out.println(JSONArray.toJSONString(arr));
    }

    //冒泡排序
    private static void bubbleSort(int[] arr) {
        if (arr == null || arr.length == 0){
            return;
        }
        //首层循环：遍历次数
        for (int i=0; i< arr.length; i++){
            //第二层循环:相邻两个元素比较
            for (int j = 1; j < arr.length -i; j++){
                if(arr[j-1] > arr[j]){ //如果前面的大于后面的,交换位置
                    //交换位置
                    int temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}
