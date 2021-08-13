package com.zbcn.algorithm;

/**
 * 旋转数组中的最小数字
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
        //如果排序数组的前面0 个元素搬到 后面，即， 排序数组本身任然是一个数组的旋转， 即数组的第一个就是最小的元素
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


    /**
     * {1,0,1,1,1,1}  ,{1,1,1,1,0,1} 也为数组旋转的特例
     * 当两个指针和他们的中间数字对应的三个值相同时，我们无法移动指针来缩小范围，只能蚕蛹 逐个遍历的方式
     * @param arr
     * @return
     */
    public static int spin3(int[] arr){
        if(arr == null || arr.length == 0){
            return -1;
        }
        int index1 = 0;
        int index2 = arr.length -1;
        //indexMid 赋值为index1 的原因时可能数组本身就是旋转数组，则index1指定的元素就是最小 元素
        int indexMid = index1;

        while (arr[index1] >= arr[index2]){
            if (index2-index1 == 1 || index2 == index1){
                //找到最小元素
                indexMid = index2;
                break;
            }
            indexMid = (index1 + index2)/2;
            //如果三个值相等
            if (arr[index1] == arr[index2]
                    && arr[index1] == arr[indexMid]){
                return findMin(arr,index1, index2);
            }
            // 最小值index1再后边
            if (arr[index1] < arr[indexMid]){
                index1 = indexMid;
            } else if (arr[index2] > arr[indexMid]){
                // 最小值再 index2 的前面
                index2 = indexMid;
            }
        }
        return arr[indexMid];
    }

    private static int findMin(int[] arr, int index1, int index2) {
        int result = arr[index1];

        for (int i = index1 + 1; i <= index2; i++){
            if (result > arr[i]){
                result = arr[i];
            }
        }
        return result;
    }
}
