package com.zbcn.algorithm;

/**
 * 求1 到 最大 的 n 位数
 * 输入一个数字n,按照顺序打印出1最大的n位十进制数，比如输入3，则打印1,2,3……999，一直到最大的三位数999.
 * @author likun
 * @since 2021/8/13 17:34
 */
public class T11_PrintMaxOfNDigits {

    public static void main(String[] args) {
       // print1ToMaxOfDigits_1(3);
        print1ToMaxOfDigits_2(3);
    }

    /**
     * 考虑大数问题
     * @param n
     */
    private static void print1ToMaxOfDigits_2(int n) {
        if (n < 0){
            return;
        }
        int[] numbers = initNum(n);
        while (!increase(numbers)){
            printNumber(numbers);
        }


    }

    /**
     * 打印数值
     * @param numbers
     */
    private static void printNumber(int[] numbers) {
        //判断是否以 0 开始
        boolean isBeginZero = true;
        for (int i = 0; i < numbers.length; i++){
            if (isBeginZero && numbers[i] != 0){
                isBeginZero = false;
            }
            if (!isBeginZero){
                System.out.print(numbers[i]);
            }
        }
        System.out.println("");
    }

    /**
     * 数值进位
     * @param numbers
     * @return
     */
    private static boolean increase(int[] numbers){

        // 判断位数是否已经超了
        boolean isOverflow  = false;
        // 进位值（一般有进位为 1， 没有进位 为 0）
        int nTakerOver = 0;
        int length = numbers.length;
        for (int i = length-1; i >=0; i--){
            // 第 i 位求和
            int sum = numbers[i]  + nTakerOver;
            // 如果为 个位 自加 1
            if (i == length-1){
                sum++;
            }
            // 当 sum 大于10时,说明要进位
            if (sum >= 10){
                // //如果 最左边一位的数值 >10,说明数据已经超过最大位了
                if (i == 0){
                    isOverflow = true;
                    break;
                }else {
                    // 进位后，当前值 减去 10
                    sum -= 10;
                    //进位1
                    nTakerOver = 1;
                    numbers[i] = sum;
                }

            }else {
                // 无进位
                numbers[i] = sum;
                break;
            }
        }
        return isOverflow;
    }


    /**
     * 初始化要打印的数值 为 数组类型
     * @param n
     * @return
     */
    private static int[] initNum(int n) {
        int[] number = new int[n];
        for (int i = 0; i < number.length; i++){
            number[i] = 0;
        }
        return number;
    }

    /**
     * 不考虑大数问题，即 数据超过 数据类型的极限
     * @param n
     */
    private static void print1ToMaxOfDigits_1(int n) {

        if (n <= 0){
            return;
        }
        // 求出最大n 位数
        int number = 1;
        int i = 0;
        while (i++ < n){
            number *= 10;
        }
        for (int j = 1; j < number; j++){
            System.out.println(j);
        }

    }
}
