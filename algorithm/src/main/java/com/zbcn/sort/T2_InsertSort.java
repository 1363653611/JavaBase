package com.zbcn.sort;

import com.alibaba.fastjson.JSONArray;

/**
 *  插入排序
 *  <br/>
 *  @author zbcn8
 *  @since  2021/4/14 16:52
 */
public class T2_InsertSort {

    public static void main(String[] args) {
        int[] arr = {8,1,7,4,6,2,5,3};
        insertSort(arr);
        System.out.println(JSONArray.toJSONString(arr));
    }

    private static void insertSort(int[] arr) {
        if (arr == null || arr.length == 0){
            return;
        }
        //默认第一个已经排好序了
        for (int i=1; i< arr.length; i++){
            int target = arr[i];
            for (int j = i-1; j>=0; j--){
                if (arr[j] > target){
                    arr[j+1] = arr[j];
                    arr[j] = target;
                }else {
                    break;
                }
            }
        }
    }
}
