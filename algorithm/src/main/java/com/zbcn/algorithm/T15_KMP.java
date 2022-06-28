package com.zbcn.algorithm;

/**
 * kmp 算法
 *
 * @author likun
 * @since 2022/5/12 8:33
 */
public class T15_KMP {

    public static void main(String[] args) {
        char[] a = {'a','b','a','b','a','e','a','b','a','c','a','b','a','b','a','c','d'};
        char[] b = {'a','b','a','b','a','c','d'};
        int n = a.length;
        int m = b.length;

        int kmp = kmp(a, n, b, m);
        System.out.println(kmp);
    }


    /**
     * a b 分别是主串和模式串，n,m 分别是主串和模式串的长度
     * @param a
     * @param n
     * @param b
     * @param m
     * @return
     */
    public static int kmp(char[] a, int n, char[] b, int m){

        int[] next = getNext(b,m);
        int j = 0;
        for(int i = 0; i < n; ++i){
            // 一直找到 a[i] 和 b[j]
            while (j > 0 && a[i] != b[j]){
                // 如果主串和模式串中的字符出现不相等，模式串要回溯的位置
                j = next[j-1] + 1;
            }
            if (a[i] == b[j]){
                // 如果主串和模式串相等，则 比较一下位
                ++j;
            }
            // 找到匹配模式串的了
            if (j == m){
                return i- m +1;
            }
        }
        // 没有找到
        return -1;

    }

    /**
     * 失效函数的求解方法（求解next 数组）
     * b 模式 模式串
     * m 模式串的长度
     */
    private static int[] getNext(char[] b, int m){
        int[] next = new int[m];
        next[0] = -1;
        int k = -1;
        for (int i = 1; i < m; ++i){
            while (k!=-1 && b[k+1] != b[i]){
                k = next[k];
            }
            if (b[k+1] == b[i]){
                ++k;
            }
            next[i] = k;
        }
        return next;
    }
}
