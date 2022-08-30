package com.zbcn.algorithm;

/**
 * 动态规划- 背包问题
 *
 * @author likun
 * @since 2022/8/30 9:55
 */
public class T19_Dynamic_Knapsack {


    /*********************************************背包问题*****************************************************/

    /****************************回溯算法实现，我们把输入的变量都定义成了成员变量************************************/
    /**
     * 存储的最大重量（背包的重量）
     */
    private int maxW = Integer.MIN_VALUE;
    // 物品重量
    private int[] weight = {2, 2, 4, 6, 3};

    // 物品个数
    private int n = 5;

    // 背包承受的最大重量
    private int w = 9;

    // 调用 f(0, 0)

    /**
     * @param i  表示将要决策第几个物品是否装入背包
     * @param cw 表示当前背包中物品的总重量
     */
    public void f(int i, int cw) {
        // i == n 表示物品都检查完了， cw = w 表示背包装满了
        if (i == n || cw == w) {
            if (cw > maxW) {
                maxW = cw;
            }
            return;
        }
        // 选择不装 第  i 个物品
        f(i + 1, cw);
        if (cw + weight[i] <= w) {
            // 选择装第 i 个物品
            f(i + 1, cw + weight[i]);
        }

    }


    /*****************递归树中，有些求解时重复的，所以使用备忘录，将重复问题保存****************/
    // 备忘录，默认值 false
    private boolean[][] mem = new boolean[5][10];

    public void f2(int i, int cw) {
        // i == n 表示物品都检查完了， cw = w 表示背包装满了
        if (i == n || cw == w) {
            if (cw > maxW) {
                maxW = cw;
            }
            return;
        }
        // 重复的结果直接返回,不重复则添加到备忘录
        if (mem[i][cw]) {
            return;
        } else {
            mem[i][cw] = true;
        }
        // 第 i 个不装入背包
        f2(i + 1, cw);
        // 选择将 第 i 个装入背包
        if (cw + weight[i] < w) {
            f(i + 1, +weight[i]);
        }
    }



    /*******************************动态规划思路 - 背包问题***************************************************************************************/

    /**
     * 用动态规划解决问题：我们把问题分解为多个阶段，每个阶段对，应一个决策。我们记录每一个阶段可达的状态集合（去掉重复的），然后通过当前阶段的状态集合，来推导下一个阶段的状态集合，动态地往前推进。
     * @param weight 物品重量
     * @param n 物品个数
     * @param w 背包可承载重量
     * @return
     */
    public int knapsack(int[] weight, int n, int w){
        //记录每层可以达到的不同状态
        boolean[][] states = new boolean[n][w+1];
        //第一行的数据要特殊处理，可以利用哨兵优化
        states[0][0] = true;
        states[0][weight[0]] = true;
        // 动态规划状态转移
        for (int i = 1; i < n; ++i){
            // 不把第 i 个物品放入背包
            for (int j = 0; j<=w; ++j){
                if (states[i-1][j] == true){
                    // 当前的重量 是之前背包中的重量
                    states[i][j] = states[i-1][j];
                }
            }
            //把 第 i 个物品放入背包
            for (int j = 0; j <= w - weight[i]; ++j){
                if (states[i-1][j] == true){
                    // 重量之和
                    states[i][j+weight[i]] = true;
                }
            }
        }
        // 我们只需要在最后一层，找一个值为 true 的最接近 w（这里是 9）的值，就是背包中物品总重量的最大值。
        for (int i = w; i >=0; --i){
            if (states[n-1][i] == true){
                return i;
            }
        }
        return 0;
    }

    public int knapsack2(int[] weight, int n, int w){
        // 默认 false
        boolean[] states = new boolean[w+1];
        //第一行数据要特殊处理，可以利用哨兵优化
        states[0] = true;
        states[weight[0]] = true;
        // 动态规划
        for (int i = 1; i < n; ++i){
            // 把 i 个物品放入背包
            //j 需要从大到小来处理。如果我们按照 j 从小到大处理的话，会出现 for 循环重复计算的问题。
            for (int j = w - weight[i]; j>0 ; --j){
                if (states[j] == true){
                    states[j + weight[i]] = true;
                }
            }
        }
        for (int i = w; i>=0; --i){
            if (states[i] == true){
                return i;
            }
        }
        return 0;

    }


    /***************************************0-1 背包问题升级版本 *************************************************************/

    // 对于一组不同重量、不同价值、不可分割的物品，我们选择将某些物品装入背包，在满足背包最大重量限制的前提下，背包中可装入物品的总价值最大是多少呢？

    /*****************************回溯算法*******************************/
    // 结果放到 maxV
    private int maxV = Integer.MIN_VALUE;
    // 物品的重量
    private int[] items = {2,2,4,6,4};
    //物品的价值
    private int[] value = {3,4,8,9,6};

    // 物品的个数
    private int n2 = 5;

    // 背包承受的最大重量
    private int w2 = 9;

    /**
     * 调用  f(0,0,0)
     * @param i 表示将要决策第几个物品是否装入背包
     * @param cw 表示当前背包中物品的总重量
     * @param cv 当前背包中物品的总价值
     */
    public void f2(int i, int cw,int cv){
        //cw ==w  表示物品装满了，i == n 表示物品都考察完了
        if (cw == w || i == n){
           // 对于 (i, cw) 相同的不同状态，那我们只需要保留 cv 值最大的那个，继续递归处理，其他状态不予考虑。
            if (maxV < cv){
                maxV = cv;
            }
            return;
        }
        // 表示选择不装 第 i 个商品
        f2(i+1, cw, cv);
        if (cw + items[i] <= w){
            // 选择装 第 i 个物品
            f2(i+1, cw + items[i], cv + value[i]);
        }
    }

    /*****************************动态规划算法*******************************/

    public int knapsack3(int[] weight, int[] value, int n, int w){
        int[][] states = new int[n][w+1];
        // 初始化states
        for (int i = 0; i < n; i++){
            for (int j = 0; j <= w+1; j++){
                states[i][j] = -1;
            }
        }
        states[0][0] = 0;
        states[0][weight[0]] = value[0];
        // 动态规划，状态转移
        for (int i = 1; i< n; ++i){
            // 不选第 i 个物品
            for (int j = 0; j <= w; ++j){
                if (states[i-1][j] >=0){
                    states[i][j] = states[i-1][j];
                }
            }
            // 选择 第 i 个物品
            for (int j = 1; j < w- weight[i]; ++j){
                if (states[i-1][j] >= 0){
                    int v = states[i-1][j] + value[i];
                    if (v > states[i][j+ weight[i]]){
                        states[i][j+weight[i]] = v;
                    }
                }
            }
        }
        // 找出最大值
        int maxvalue = -1;
        for (int j = 0; j <= w; ++j){
            if (states[n-1][j] > maxvalue){
                maxvalue = states[n-1][j];
            }
        }
        return maxvalue;
    }


    public static void main(String[] args) {
        T19_Dynamic_Knapsack t19_dynamicKnapsack = new T19_Dynamic_Knapsack();
        t19_dynamicKnapsack.f(0, 0);
        System.out.println("回溯算法解决问题：" + t19_dynamicKnapsack.maxW);

        // 物品重量
        int[] weight = {2, 2, 4, 6, 3};

        // 物品个数
        int n = 5;

        // 背包承受的最大重量
        int w = 9;
        int knapsack = t19_dynamicKnapsack.knapsack(weight, n, w);
        System.out.println("背包算法解决问题：" + knapsack);
        int knapsack2 = t19_dynamicKnapsack.knapsack2(weight, n, w);
        System.out.println("背包算法解决问题2：" + knapsack2);
    }




}
