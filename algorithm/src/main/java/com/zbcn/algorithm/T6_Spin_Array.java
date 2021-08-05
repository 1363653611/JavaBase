package com.zbcn.algorithm;

/**
 * 旋转数组
 */
public class T6_Spin_Array {

    public static void main(String[] args) {
        int[] arr = {3,4,5,0,1,2};
        int spin = spin2(arr);
        System.out.println(spin);
    }

    //找出最小的元素的索引位置
    public static int spin(int[] arr){
        if(arr == null || arr.length == 0){
            return -1;
        }
        //两个指针
        int index1 = 0;
        int index2 = arr.length-1;
        //如果排序数组的前面0 个元素搬到 后面，即数组的第一个就是最小的元素
        int indexMid = index1;

        while (arr[index1] >= arr[index2]){
            //相邻元素或者同一个元素
            if(index2 - index1 == 1 || index2 - index1 == 0){
                indexMid = index2;
                break;
            }
            // 中间元素位置
            indexMid = (index1 + index2)/2;
            // 第一个子数组当前元素小于 indexMid 指定的 元素，说明最小的的元素在 indexMid的后面
            if(arr[indexMid] > arr[index1]){
                index1 = indexMid;

            // 第二个子数组的 index2 指定的元素大于 indexMid指定的元素，说明最小的在indexMid的前面
            }else if(arr[indexMid] < arr[index2]){
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
