package com.zbcn.sort;

import com.alibaba.fastjson.JSONArray;

import java.nio.channels.SelectionKey;

/**
 *  希尔排序
 *  <br/>
 *  @author zbcn8
 *  @since  2021/4/14 17:08
 */
public class T2_ShellSort {

    public static void main(String[] args) {
        int[] arr = {8,1,7,4,6,2,5,3};
        shellSort(arr);
        System.out.println(JSONArray.toJSONString(arr));
    }

    /**
     * 指定步长的插入排序:
     * 1. 计算初始步长 (arr的长度除于 2)
     *     2. 当步长 大于 1时,循环处理
     *     3. 以步长为初始值,结束值为数组的长度,循环arr
     *     4. 当 当前值 i - d 大于 等于 0时,按照插入法 排序 步长为 d 的数据
     *     5. 修改步长
     * @param arr
     */
    private static void shellSort(int[] arr) {
        if(arr == null ||arr.length == 0){
            return;
        }
        //初始步长
        int step = arr.length/2;
        while (step >=1){
            //按照指定的步长插入排序
            for (int i = step; i < arr.length;i++){
                int temp = arr[i];
                int j = i- step;
                while (j >= 0){
                    if (arr[j] > temp){
                        arr[j+step] = arr[j];
                        arr[j] = temp;
                    }
                    j -= step;
                }
            }
            step = step/2;
        }
    }

}
