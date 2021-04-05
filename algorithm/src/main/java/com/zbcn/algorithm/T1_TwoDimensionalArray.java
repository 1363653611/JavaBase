package com.zbcn.algorithm;

/**
 * 二维数组中查找需要的数据
 * 题目：在一个二维数组中，每一行都按照从左到右的递增顺序排序，每一列都按照从上到下的递增顺序排序，输入一个二维数组，和一个整数，
 * 判断该二维数组中是否含有 该整数
 * <br/>
 *
 * @author zbcn8
 * @since 2021/3/22 11:30
 */
public class T1_TwoDimensionalArray {

    /**
     *  1, 2, 8,  9
     *  2, 4, 9,  12
     *  4, 7, 10, 13
     *  6, 8, 11, 15
     * @param args
     */
    public static void main(String[] args) {
        int[][] a = {{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
        int columns = a.length;
        int rows = a[0] != null ? a[0].length : 0;
        boolean b = find2(a, rows, columns, 10);
        System.out.println(b);
        System.out.println(a[1][2]);
    }

    //从右上角进行检查
    private static boolean find(int[][] array, int rows, int columns, int target) {
        boolean found = false;
        if (array != null && rows > 0 && columns > 0) {
            int row = 0;
            int column = columns - 1;
            while (row < rows && column > 0) {
                int dest = array[row][column];
                if (dest == target) {
                    found = true;
                    break;
                } else if (target < dest) {
                    column--;
                } else if (target > dest) {
                    row++;
                }
            }
        }
        return found;
    }

    // 从左下角进行检查
    public static boolean find2(int[][] array, int rows, int columns, int target) {
        boolean found = false;
        if (array != null && rows > 0 && columns > 0) {
            int row = rows-1;
            int column = 0;
            while (row > 0 && column < columns) {
                int dest = array[row][column];
                if(dest == target){
                    found = true;
                    break;
                }else if( dest < target){
                    column++;
                }else{
                   row--;
                }
            }
        }
        return found;
    }
}
