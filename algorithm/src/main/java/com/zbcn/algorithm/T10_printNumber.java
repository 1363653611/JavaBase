package com.zbcn.algorithm;

/**
 * 打印 1 到最大的 n 位数
 * <br/>
 *
 * @author zbcn8
 * @since 2021/4/11 21:32
 */
public class T10_printNumber {

    public static void main(String[] args) {
        int n = 3;
        //print1ToMaxOfDigits_1(n);
        print1ToMaxOfDigits_2(n);
    }

    private static void print1ToMaxOfDigits_2(int n) {
        if (n < 0) {
            return;
        }
        //将数组中的每一位都初始化为 0
        char[] number = initNun(n);
        while (!increment(number)){
            printNumber(number);
        }
    }

    //打印数值
    private static void printNumber(char[] number) {
        //判断是否以 0 开始
        boolean isBeginZero = true;
        for (int i = 0; i < number.length; i++){
            if (isBeginZero && number[i] != '0'){
                isBeginZero = false;
            }
            if (!isBeginZero){
                System.out.print(number[i]);
            }
        }
        System.out.println("");
    }

    private static boolean increment(char[] number) {
        // 判断位数是否已经超了
        boolean isOverflow = false;
        //进位值(一般有进位 为 1, 没有进位 为 0)
        int nTakeOver = 0;
        int length = number.length;
        for (int i = length -1; i >= 0; i--){
            // 第n 位数求和
            int sum = number[i] - '0' + nTakeOver;
            if (i == length -1){ // 如果为 个位 自加 1
                sum++;
            }
            if (sum >= 10){ //当 sum 大于10时,说明要进位
                if (i == 0){ //如果 最左边一位的数值 >10,说明数据已经超过最大位了
                    isOverflow = true;
                    break;
                }else {
                    //进位后,当前值
                    sum -= 10;
                    //进位 值为 1
                    nTakeOver = 1;
                    number[i] = (char) ('0' + sum);
                }
            }else {
                //无进位
                number[i] = (char) ('0' + sum);
                break;
            }
        }
        return isOverflow;
    }

    private static char[] initNun(int n) {
        char[] number = new char[n];
        for (int i = 0; i < n; i++){
            number[i] = '0';
        }
        return number;
    }

    //如果不考虑数组越界问题
    private static void print1ToMaxOfDigits_1(int n) {
        if (n <= 0) {
            return;
        }
        int number = 1;
        int i = 0;
        while (i++ < n) {
            number *= 10;
        }
        for (int j = 1; j < number; j++) {
            System.out.println(j);
        }
    }
}
