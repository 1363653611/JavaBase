package com.zbcn.test;

import lombok.Data;

/**
 * 背包问题 - 暴力解法（回溯）
 *
 * @author likun
 * @since 2022/12/28 17:59
 */
@Data
public class KnapsackDemo {

    public static void main(String[] args) {
        KnapsackDemo demo = new KnapsackDemo();
        int[] a = {12,32,44,56,78,99,32,1,33,4};
        demo.f(0,0,a,10,100);
        System.out.println(demo.getMaxW());
    }

    // 存储背包中物品的最大值
    private int maxW = Integer.MIN_VALUE;

    //我们把物品依次排列，整个问题就分解了 n 个阶段，每个阶段对应一个物品怎么选，先对第一个物品进行处理，选择装进去或者不装进去，然后再递归的处理身下的物品
    // cw 表示当前已经装进去的物品的质量和
    // i 表示考察到哪个物品了
    //w 表示背包重量
    // items 表示每个物品的重量
    // n 表示物品的个数
    // 假设背包可承受的重量是 w = 100 , 物品个数 n = 10; 物品重量存储在数组 a 中，  调用方式： f(0,0,a,10,100)
    public void f(int i, int cw, int[] items, int n, int w){

        // 物品装满，或者 已经考察完所有的项目
        if (i==n || w == cw){
            if (cw > maxW){
                maxW = cw;
            }
            return;
        }
        // 处理下一个物品
        f(i + 1, cw,items, n,w);

        // 处理当前物品,已经超过可以背包承受的重量时，就不要再装了
        if (cw + items[i] <= w){
            // 找到最接近 w 的
            f(i+1, cw + items[i],items,n,w);
        }


    }
}
