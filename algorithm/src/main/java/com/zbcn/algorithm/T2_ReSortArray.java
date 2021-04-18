package com.zbcn.algorithm;
/**
 *  调整数组顺序，使得奇数在偶数的前面
 *  <br/>
 *  @author zbcn8
 *  @since  2021/4/13 15:12
 */
public class T2_ReSortArray {

    public static void main(String[] args) {
        int[] arr = {1,3,4,5,6,7};
//        sortArray(arr);
//        System.out.println(0x1);
//        System.out.println(7&0x1);
//        System.out.println(6&0x1);
        sortArray(arr);
        System.out.println(arr);
    }

    private static void sortArray(int[] arr) {
        if(arr == null){
            return;
        }
        //第一个指针指向开始
        int preIndex = 0;
        //第二个指针指向数组的尾部位置
        int tailIndex = arr.length-1;
        while (preIndex <tailIndex){
            //向后移动 preIndex ，直到他指向 偶数位
            while (preIndex < tailIndex && (arr[preIndex] & 0x1) != 0){//如果为奇数则自增
                preIndex ++;
            }
            //向前移动 tailIndex ，直到他指向奇数位
            while (preIndex < tailIndex && (arr[tailIndex] & 0x1) == 0){//如果为偶数则自减
                tailIndex--;
            }
            if(preIndex < tailIndex){
                int temp = arr[preIndex];
                arr[preIndex] = arr[tailIndex];
                arr[tailIndex] = temp;

            }
        }
    }
}
