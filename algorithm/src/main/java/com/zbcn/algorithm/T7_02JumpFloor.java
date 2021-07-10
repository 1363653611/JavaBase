package com.zbcn.algorithm;

/**
 * 青蛙跳台阶（1阶或者2阶）
 * 题目描述：一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级。求该青蛙跳上一
 * 个 n 级的台阶总共有多少种跳法
 * @author likun
 * @since 2021/6/22 19:56
 */
public class T7_02JumpFloor {

    public static void main(String[] args) {

        //解题思路:dp（dynamic pogramming）
        // 1. 定义变量： 假设 青蛙跳上n 个台阶有 f(n) 种跳法，
        //2. 寻找初始值
        // n = 0  f(0) = 0  没有台阶，没有跳法
        // n = 1 f(1) = 1  有一个台阶，只有一种跳法
        // n = 2 f(2) = 2  有2 个台阶，有两种跳法
        // n = 3 f(3) = f(1) + f(2)  将一个台阶和两个台阶的跳法加起来
        // 寻找表达式： f(n) = f(n-2) + f(n-1)
        int n = 10;
        int i = jumpFloor(n);
        System.out.println("总共的跳法有："+i);


    }

    private static int jumpFloor(int n) {
        if(n < 0){
            return 0;
        }
        if (n < 3){
            return n;
        }
        int result = 0;
        int preOne = 1;
        int preTwo = 2;
        for(int i = 3; i<= n; i++){
            result = preOne + preTwo;
            preTwo = preOne;
            preOne = result;
        }
        return result;
    }
}
