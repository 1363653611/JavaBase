package com.zbcn.algorithm;

/**
 *  位移运算：判断一个数字转换成2进制后 包含1 的个数:00001001
 *  <br/>
 *  @author zbcn8
 *  @since  2021/4/5 18:55
 */
public class T7_01Bit_Move {

    public static void main(String[] args) {
        int i = numberOf_13(9);
        int i1 = numberOf_1(-10);
        System.out.println(i1);
    }

    //可能回引起死循环： 如果输入为负数的时候, 右移时，最高位 要 置为 1， 所以 回导致 输入的值的右边变成无限多个1 ，-> 0xFFFFFFFF
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

    /**
     * 将1 左移，避免了 将 输入值 右移 引起的 死循环问题
     * @param n
     * @return
     */
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

    /**
     * 思路： a&(a-1) 会将最右侧的 1 变为0，直到 a 等于 0
     * analyze：一个整数 n 和 自己 减去 1（n-） 做 与运算，作用是：将 该整数 的 最右边的 1 变成 0，
     * 该数的 二进制表示中有多少个这样的 1 就有多少个 这样的运算。
     * @param n
     * @return
     */
    private static int numberOf_13(int n){
        int count = 0;
        while (n != 0){
            count++;
            n = (n-1) & n;
        }
        return count;
    }
}
