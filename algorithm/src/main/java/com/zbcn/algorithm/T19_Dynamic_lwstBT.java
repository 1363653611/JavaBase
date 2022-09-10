package com.zbcn.algorithm;

/**
 *动态规划：如何量化两个字符串的相似度？
 *
 */
public class T19_Dynamic_lwstBT {

    private char[] a = "mitcmu".toCharArray();
    private char[] b = "mtacnu".toCharArray();
    private int n = 6;

    private int m = 6;

    //存储结果
    private int minDist = Integer.MAX_VALUE;

    // 调用： lwstBT(0, 0, 0);

    /**
     *
     * 回溯算法
     * 对于 (i, j) 相同的节点，我们只需要保留 edist 最小的，继续递归处理就可以了，剩下的节点都可以舍弃。
     * @param i
     * @param j
     * @param edist 处理到 a[i] 和 b[j] 时，已经执行的编辑操作的次数。
     */
    public void lwstBT(int i, int j, int edist){
        if (i == n || j == m){
            if (i < n){
                edist += (n-i);
            }
            if (j < m){
                edist += (m-j);
            }

            if (edist <minDist){
                minDist = edist;
            }
            return;
        }
        // 两个字符串匹配
        if (a[i] == b[j]){
            lwstBT(i+1, j+1, edist);
        }else {
            // 删除 a[i] 或者 b[j] 前添加一个字符
            lwstBT(i+1,j,edist +1);
            // 删除 b[j] 或者 a[i] 前添加一个字符
            lwstBT(i, j+1, edist +1);
            // 将 a[i] 和 b[j] 替换为相同字符
            lwstBT(i+1, j +1, edist +1);
        }
    }



}
