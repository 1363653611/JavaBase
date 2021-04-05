package com.zbcn.algorithm;

/**
 *  位移运算：判断一个数字转换成2进制后 包含1 的个数:00001001
 *  <br/>
 *  @author zbcn8
 *  @since  2021/4/5 18:55
 */
public class T8_Bit_Move {

    public static void main(String[] args) {
        int i = numberOf_13(9);
        System.out.println(i);
    }

    //可能回引起死循环： 如果输入为负数的时候
    private static int numberOf_1(int n) {
        //计算 1 的数量
        int count = 0;

        while (n > 0){
            if((n & 1) == 1){
                count ++;
            }
            n = n >> 1;
        }
        return count;
    }

    private static int numberOf_12(int n){
        int count = 0;
        int flag = 1;
        while (flag <= n+1){
            if((n & flag) != 0){
                count++;
            }
            flag = flag << 1;
        }
        return count;
    }

    private static int numberOf_13(int n){
        int count = 0;
        while (n != 0){
            count++;
            n = (n-1) & n;
        }
        return count;
    }
}
