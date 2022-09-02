package com.zbcn.algorithm;

/**
 * 动态规划,最短路径
 * 假设我们有一个 n 乘以 n 的矩阵 w[n][n]。矩阵存储的都是正整数。棋子起始位置在左上
 * 角,终止位置在右下角。我们将棋子从左上角移动到右下角。每次只能向右或者向下移动一
 * 位。从左上角到右下角,会有很多不同的路径可以走。我们把每条路径经过的数字加起来看
 * 作路径的长度。那从左上角移动到右下角的最短路径长度是多少呢？
 *
 * @author likun
 * @since 2022/8/31 17:23
 */
public class T19_Dynamic_ShortPath {

    /**
     * 全局变量：最短路径
     */
    private int minDist = Integer.MAX_VALUE;

    /**
     * 回溯方法解决：
     * 调用方式：minDistBacktracing(0, 0, 0, w, n)
     * @param i  表示 行
     * @param j  表示 列
     * @param dist 表示从起点到达（i,j） 的路径长度
     * @param w
     * @param n
     */
    public void minDistBT(int i, int j, int dist, int[][] w, int n){

        // 到达了 n-1,n-1 这个位置了
        if (i == n && j == n){
            if (dist < minDist){
                minDist = dist;
            }
            return;
        }
        // 往下走,更新 i = i +1,j = j
        if (i < n){
            minDistBT(i +1, j, dist + w[i][j],w,n);
        }
        // 往右走,更新 i = i, j = j+1
        if (j < n){
            minDistBT(i, j+1, dist + w[i][j],w,n);
        }
    }


    /**
     * 动态规划  求出矩阵中的 最短路径    --》 状态转移表发
     * @param matrix
     * @param n
     * @return
     */
    public int mindDistPt(int[][] matrix, int n){
        int [][] states = new int[n][n];
        int sum = 0;
        // 初始化 states 的第一行数据
        for (int j = 0; j < n; ++j){
            sum += matrix[0][j];
            states[0][j] = sum;
        }
        // 初始化 states 的第一列数据
        sum = 0;
        for (int i = 0; i < n; ++i){
            sum += matrix[i][0];
            states[i][0] = sum;
        }

        // 动态规划
        for (int i = 1; i < n; ++i){
            for (int j = 1; j < n; ++j){
                states[i][j] = Math.min(states[i][j-1], states[i-1][j]);
            }
        }
        return states[n][n];
    }



    // 状态转移方程法:  有点类似递归的解题思路。我们需要分析,某个问题如何通过子问题来递归
    //求解,也就是所谓的最优子结构。根据最优子结构,写出递归公式,也就是所谓的状态转移
    //方程。

    // 状态转移方程是解决动态规划的关键。

    private int[][] matrix = {
            {1,3,5,9},
            {2,1,3,4},
            {5,2,6,7},
            {6,8,4,3}
    };

    private int n = 4;

    private int[][] mem = new int[n][n];

    // 调用 minDist(n-1, n-1);

    /**
     *
     * @param i
     * @param j
     * @return
     */
    public int minDist(int i, int j){
        if (i == 0 && j == 0){
            return matrix[i][j];
        }
        if (mem[i][j] > 0){
            return mem[i][j];
        }

        int minLeft =Integer.MAX_VALUE;

        if ( j - 1 >= 0){
            minLeft = minDist(i,j-1);
        }
        int minUp = Integer.MAX_VALUE;
        if (i-1 >= 0){
            minUp = minDist(i-1,j);
        }
        int currMInDist = matrix[i][j] + Math.min(minLeft,minUp);

        mem[i][j] = currMInDist;

        return currMInDist;
    }



}
