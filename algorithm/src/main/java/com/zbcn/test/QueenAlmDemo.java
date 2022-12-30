package com.zbcn.test;

import java.util.Objects;

/**
 * 8 皇后问题
 */
public class QueenAlmDemo {

    // 全局成员变量， 下表表示 行，值 表示 queen 存储在哪一列
    private int[] result = new int[8];

    public static void main(String[] args) {
        QueenAlmDemo queenAlmDemo = new QueenAlmDemo();
        queenAlmDemo.cal8queens(0);
    }
    public void cal8queens(int row){
        // 表示 8 个棋子都设置好了
        if (row == 8){
            printQueens(result);
            return;
        }
        //每一行都有 8 种放法
        for (int column = 0; column < 8; ++column){
            if (isOk(row,column)){
                result[row] = column;
                // 考察下一行
                cal8queens(row + 1);
            }
        }

    }


    public boolean isOk(int row, int column){

        // 左侧的元素
        int leftUp = column - 1;
        // 右侧的元素
        int rightUp = column + 1;

        // 逐行往上考察每一行
        for (int i = row-1; i >= 0; --i){
            // 判断i 行的 column 有没有元素
            if (result[i] == column){
                return false;
            }
            //判断左上侧对角线 的 leftUp上有棋子吗
            if (leftUp >= 0){
                if (result[i] == leftUp){
                    return false;
                }
            }
            // 判断右上对角线上有棋子吗
            if (rightUp < 8){
                if (result[i] == rightUp){
                    return false;
                }
            }
            --leftUp ;
            ++rightUp;

        }
        return true;

    }

    public void printQueens(int[] result){
        // 8 行
        for (int row = 0; row < 8; row++){
            // Q 的位置
            int desColumn = result[row];
            // 8 列
            for (int column = 0; column < 8; column++){
                if ( desColumn == column){
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
