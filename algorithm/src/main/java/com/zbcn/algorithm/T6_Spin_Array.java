package com.zbcn.algorithm;

/**
 * 旋转数组
 */
public class T6_Spin_Array {

    public static void main(String[] args) {
        int[] arr = {3,4,5,1,2};
        int spin = spin2(arr);
        System.out.println(spin);
    }

    public static int spin(int[] arr){
        if(arr == null || arr.length == 0){
            return -1;
        }
        //两个指针
        int index1 = 0;
        int index2 = arr.length-1;
        int indexMid = index1;//如果排序数组的前面0 个元素搬到 后面，即数组的第一个就是最小的元素

        while (arr[index1] >= arr[index2]){
            if(index2 - index1 == 1 || index2 - index1 == 0){//相邻元素
                indexMid = index2;
                break;
            }
            // 中间元素位置
            indexMid = (index1 + index2)/2;
            if(arr[indexMid] > arr[index1]){// 第一个子数组当前元素小于 indexMid 指定的 元素，说明最小的的元素在 indexMid的后面
                index1 = indexMid;
            }else if(arr[indexMid] < arr[index2]){// 第二个子数组的 index2 指定的元素大于 indexMid指定的元素，说明最小的在indexMid的前面
                index2 = indexMid;
            }
        }
        return arr[indexMid];
    }


    public static int spin2(int[] arr){
        if(arr == null || arr.length == 0){
            return -1;//空数组返回-1
        }
        int start = 0;
        int end = arr.length-1;
        //如果只有一个元素
        if(start == end){
            return arr[start];
        }
        //定位旋转点
        while (start < end){
            if (arr[start] < arr[end]){
                break;
            }else {
                start++;
                end--;
            }
        }
        if(start <= end){
            return arr[end] > arr[end +1]? arr[end+1]:arr[start];
        }else {
            return arr[start];
        }

    }
}
