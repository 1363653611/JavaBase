package com.zbcn.algorithm;

/**
 * 动态规划解决双11 问题
 * <p>
 * eg:淘宝的“双十一”购物节有各种促销活动，比如“满 200 元减 50 元”。假设你女朋友的
 * 购物车中有 n 个（n>100）想买的商品，她希望从里面选几个，在凑够满减条件的前提
 * 下，让选出来的商品价格总和最大程度地接近满减条件（200 元），这样就可以极大限度
 * 地“薅羊毛”
 *
 * @author likun
 * @since 2022/8/30 14:33
 */
public class T19_Dynamic_demo {


    /**
     * @param items 商品价格
     * @param n     商品数量
     * @param w     表示满减条件， 比如200
     */
    public static void double11advance(int[] items, int n, int w) {
        // 3*w +1 表示 超过 3倍 薅羊毛就没价值了
        boolean[][] states = new boolean[n][3 * w + 1];
        // 第一行数据特殊处理
        states[0][0] = true;
        states[0][items[0]] = true;

        // 动态规划
        for (int i = 1; i < n; ++i) {
            // 不购买第i个商品
            for (int j = 0; j <= 3 * w; ++j) {
                if (states[i - 1][j] == true) {
                    states[i][j] = states[i - 1][j];
                }
            }
            // 购买第 i 个商品
            for (int j = 0; j + items[i] < 3 * w; ++j) {
                if (states[i - 1][j] == true) {

                    states[i][j + items[i]] = true;
                }
            }
        }

        // 找出要购买的商品
        int j = w;
        while (j < 3 * w + 1) {
            // 找到结果 大于等于 w 的最小值
            if (states[n - 1][j] == true) {
                break;
            }
            ++j;
        }
        // 没有可行解
        if (j == 3*w+1){
            return;
        }
        // i 表示二位数组中的行，j 表示列
        for (int i = n-1; i > 1; --i){
            if (j - items[i] >= 0 && states[i-1][j-items[i]] == true){
                // 改商品可以购买
                System.out.println(items[i] + "   购买");
                j = j - items[i];
            }// else 没有购买这个商品， j 不变
        }
        if (j != 0){
            System.out.println(items[0] + "  ");
        }
    }

    public static void main(String[] args) {


    }
}
