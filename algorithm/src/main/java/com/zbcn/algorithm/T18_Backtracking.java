package com.zbcn.algorithm;

/**
 * 回溯算法
 *
 * @author likun
 * @since 2022/8/11 15:58
 */
public class T18_Backtracking {


    /**
     * 我们有一个 8x8 的棋盘，希望往里放 8 个棋子（皇后），每个棋子所在的行、列、对角线
     * 都不能有另一个棋子
     *
     * @author likun
     * @since 2022/8/11 17:43
     */
    static class QueenAlm{
        /**
         * 全局成员变量，下标表示行，值 表示 queue 存储在哪一列
         */
        private int[] result = new int[8];

        public void cal8queues(int row){
            //如果 8 个 皇后都放好了，则打印结果
            if (row == 8){
                printQueue(result);
                // 8 行棋子都放好了，已经没法再往下递归了，所以就 return
                return;
            }
            // 每一行都有8种放发
            for (int column=0; column < 8;++column){
                // 有些放法不满足要求
                if (isOk(row,column)){
                    // 第 row 行的棋子放到了 column 列
                    result[row] = column;
                    //考察下一行
                    cal8queues(row+1);
                }
            }
        }

        /**
         * 第 row 行的棋子放到了 column 列
         * @param row
         * @param column
         * @return
         */
        private boolean isOk(int row, int column) {
            int leftup = column -1, rightup = column+1;
            // 逐行往上考察每一行
            for (int i = row-1; i >= 0; --i){
                // 第 i 行的 column 列有棋子吗？
                if (result[i] == column){
                    return false;
                }
                // 考察左上对角线：第 i 行 leftup 列有棋子吗？
                if (leftup >= 0){
                    if (result[i] == leftup){
                        return false;
                    }
                }
                // 考察右上对角线：第 i 行 rightup 列有棋子吗？
                if (rightup < 8){
                    if (result[i] == rightup){
                        return false;
                    }
                }
                --leftup;
                ++rightup;
            }
            return true;
        }

        /**
         * 打印出一个二维矩阵
         * @param result
         */
        private void printQueue(int[] result) {
            for (int row = 0; row < 8; ++row){
                for (int column = 0; column < 8; ++column){
                    if (result[row] == column){
                        System.out.print("Q ");
                    }else {
                        System.out.print("* ");
                    }
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    /**
     * 0-1 背包
     * 我们有一个背包，背包总的承载重
     * 量是 Wkg。现在我们有 n 个物品，每个物品的重量不等，并且不可分割。我们现在期望选
     * 择几件物品，装载到背包中。在不超过背包所能装载重量的前提下，如何让背包中物品的总
     * 重量最大？
     * 
     * @author likun
     * @since 2022/8/11 17:51
     */
    static class OneZeroPkg{
        // 存储背包中物品总重量的最大值
        public int maxW = Integer.MIN_VALUE;

        /**
         *
         * @param i 表示考察到哪个哪个物品了
         * @param cw 表示当前已经装进去的物品的重量和
         * @param items 表示每个物品的重量
         * @param n 表示物品的个数
         * @param w 背包重量
         */
        public void  f(int i,int cw, int[] items, int n, int w){
            // cw==w 表示装满了 ;i==n 表示已经考察完所有的物品
            if (cw == w || i == n){
                if (cw >maxW){
                    maxW = cw;
                }
                return;
            }
            f(i +1, cw,items,n,w);
            // 已经超过可以背包承受的重量的时候，就不要再装了
            if (cw + items[i] <= w){
                f(i+1,cw + items[i],items,n,w);
            }

        }
        
    }

    /**
     * 正则表达式中，最重要的就是通配符，通配符结合在一起，可以表达非常丰富的语义。为了
     * 方便讲解，我假设正表达式中只包含“*”和“?”这两种通配符，并且对这两个通配符的语
     * 义稍微做些改变，其中，“*”匹配任意多个（大于等于 0 个）任意字符，“?”匹配零个
     * 或者一个任意字符。基于以上背景假设，我们看下，如何用回溯算法，判断一个给定的文
     * 本，能否跟给定的正则表达式匹配？
     *
     * @author likun
     * @since 2022/8/11 18:11
     */
    static class SelfPattern{

        private boolean matched = false;

        private char[] pattern; // 正则表达式

        private int plen; // 正则表达式长度

        public SelfPattern(char[] pattern, int plen) {
            this.pattern = pattern;
            this.plen = plen;
        }

        /**
         * 正则匹配
         * @param text  文本串
         * @param tlen 长度
         * @return
         */
        public boolean match(char[] text, int tlen){
            matched = false;
            rmatch(0,0, text,tlen);
            return matched;
        }

        /**
         * 字符串重新匹配
         * @param ti 字符串起始位置
         * @param pj 模式串起始位置
         * @param text 字符串
         * @param tlen 字符串长度
         */
        private void rmatch(int ti, int pj, char[] text, int tlen) {
            // 如果已经 匹配了，就不要继续递归了
            if (matched){
                return;
            }
            // 正则表达式到结尾了
            if (pj == plen){
                // 文本串也到结尾了
                if (ti == tlen){
                    matched = true;
                }
                return;
            }
            // 匹配 任意个字符
            if (pattern[pj] == '*'){
                for (int k= 0; k <= tlen-ti; ++k){
                    rmatch(ti +k, pj+1, text,tlen);
                }
            }else if (pattern[pj] == '?'){  // ? 匹配 0 个或者 1 个字符
                // 匹配 0 个
                rmatch(ti,pj +1, text,tlen);
                // 一个
                rmatch(ti+1,pj +1, text,tlen);

            }else if (ti < tlen && pattern[pj] == text[ti]){ // 纯字符匹配才行
                rmatch(ti+1,pj+1,text,tlen);
            }

        }


    }



    public static void main(String[] args) {
        QueenAlm queenAlm = new QueenAlm();
        queenAlm.cal8queues(0);
        OneZeroPkg oneZeroPkg = new OneZeroPkg();
        // 假设背包可承受重量 100，物品个数 10，物品重量存储在数组 a 中，那可以这样调用函数：
        // oneZeroPkg. f(0, 0, a, 10, 100);
    }


}
