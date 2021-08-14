package com.zbcn.algorithm;

/**
 * 输入两个整数，m 和 n, 计算改变m 的二进制中的多少位才能得到n. 比如：10 的二进制表示：1010，13 的二进制表示：1101.
 *
 * @author likun
 * @since 2021/8/12 9:12
 */
public class T10_BitCalDemo {

    public static void main(String[] args) {
        int n = 10;
        int m = 13;
        int i = twoData(n, m);
        System.out.println("需要改变：" + i + " 位才能相等 ");
    }

    private static int twoData(int n, int m) {
        int xorData = n ^ m;
        return numOf1(xorData);
    }

    /**
     * 1 的个数
     * @param xorData
     */
    private static int numOf1(int xorData) {
        int count = 0;
        while (xorData != 0){
            // xorData & (xorData -1) 作用是把该整数的最右边的一个 1变成 0.
            xorData = xorData & (xorData -1);
            count ++;
        }
        return count;
    }
}
