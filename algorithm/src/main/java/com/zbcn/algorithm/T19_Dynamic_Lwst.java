package com.zbcn.algorithm;

import java.util.Locale;

/**
 * 动态规划：如何实现搜索引擎中的拼写纠错功能
 *
 * @author likun
 * @since 2022/9/5 11:10
 */
public class T19_Dynamic_Lwst {

    /****************************************/

       // 莱文斯坦距离（Levenshtein distance）

    /***************************************/

    /*****************************回溯算法*********************************/

    private char[] a = "mitcmu".toCharArray();

    private char[] b = "mtacnu".toCharArray();

    private int n = 6;

    private int m = 6;

    private int minDist = Integer.MAX_VALUE;

    // 调用方式 lwstBT(0, 0, 0);
    public void lwstBT(int i, int j, int edist) {
        if (i == n || j == m) {
            if (i < n) {
                edist += (n - i);
            }
            if (j < m) {
                edist += (m - j);
            }
            if (edist < minDist) {
                minDist = edist;
            }
            return;
        }
        // 如果两个字符匹配
        if (a[i] == b[i]) {
            lwstBT(i + 1, j + 1, edist);
        } else {
            // 两个字符不匹配
            // 删除 a[i] 或者 b[j] 前添加一个字符
            lwstBT(i + 1, j, edist + 1);
            //删除 b[j] 或者 a[i] 前添加一个字符
            lwstBT(i, j - 1, edist + 1);
            // 将 a[i] 和 b[j] 替换为相同字符
            lwstBT(i + 1, j + 1, edist + 1);
        }

    }

    /****************************动态规划*****************************************/
    //莱文斯坦距离（Levenshtein distance）
    public int lwstDP(char[] a, int n, char[] b, int m) {
        int[][] minDist = new int[n][m];
        // 初始化 第 0 行， a[0..0] 与 b[0..j] 的编辑距离
        for (int j = 0; j < m; ++j) {
            if (a[0] == b[j]) {
                minDist[0][j] = j;
            } else if (j != 0) {
                minDist[0][j] = minDist[0][j - 1] + 1;
            } else {
                minDist[0][j] = 1;
            }
        }
        // 初始化 第 0 列，a[0..i]与 b[0..0] 的编辑距离
        for (int i = 1; i < n; ++i) {
            if (a[i] == b[0]) {
                minDist[i][0] = i;
            }
            if (i != 0) {
                minDist[i][0] = minDist[i - 1][0] + 1;
            } else {
                minDist[i][0] = 1;
            }
        }
        //按行填表
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < m; ++j) {
                if (a[i] == b[j]) {
                    minDist[i][j] = min(minDist[i - 1][j] + 1, minDist[i][j - 1] + 1, minDist[i - 1][j - 1]);
                } else {
                    minDist[i][j] = min(minDist[i - 1][j] + 1, minDist[i][j - 1] + 1, minDist[i - 1][j - 1] + 1);
                }

            }
        }
        return minDist[n - 1][m - 1];
    }

    private int min(int x, int y, int z) {
        int minV = Integer.MAX_VALUE;
        if (x < minV) {
            minV = x;
        }
        if (y < minV) {
            minV = y;
        }
        if (z < minV) {
            minV = z;
        }
        return minV;
    }



    /****************************************/

    // 最长公共子串长度（Longest common substring length）

    /***************************************/

    public int lcs(char[] a, int n, char[] b, int m){
        int[][] maxlcs = new int[n][m];
        // 初始化 第 0 行：a[0..0] 与 b[0..j] 的 maxlcs
        for (int j = 0; j < m; j++){
            if (a[0] == b[j]){
                maxlcs[0][j] = 1;
            }else if (j != 0){
                maxlcs[0][j] = maxlcs[0][j-1];
            }else {
                maxlcs[0][j] = 0;
            }
        }
        // 初始化第 0 列：a[0..i] 与 b[0..0] 的 maxlcs
        for (int i = 1; i< n; ++i){
            if (b[0] == a[i]){
                // 第一个子字符相等
                maxlcs[i][0] = 1;
            }else if (i != 0){
                // 如果不是第一个子字符
                maxlcs[i][0] = maxlcs[i-1][0];
            }else {
                // 第一个子字符不相等
                maxlcs[i][0] = 0;
            }
        }
        // 填表
        for (int i = 1;  i < n; ++i){
            for (int j = 1; j < m; ++j){
                if (a[i] == b[i]){
                    max(maxlcs[i-1][j], maxlcs[i][j-1], maxlcs[i-1][j-1] + 1);
                }else {
                    max(maxlcs[i-1][j], maxlcs[i][j-1], maxlcs[i-1][j-1]);
                }
            }
        }
        return maxlcs[n-1][m-1];
    }

    private int max(int x, int y, int z) {
        int maxV = Integer.MIN_VALUE;

        if (x > maxV){
            maxV = x;
        }
        if (y > maxV){
            maxV= y;
        }
        if (z > maxV){
            maxV = z;
        }
        return maxV;
    }
}
