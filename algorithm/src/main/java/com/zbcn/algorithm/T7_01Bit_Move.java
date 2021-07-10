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

    //可能回引起死循环： 如果输入为负数的时候,无法计算 出1 的个数
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

    /**
     * 思路： a&(a-1) 会将最右侧的 1 变为0，直到 a 等于 0
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
