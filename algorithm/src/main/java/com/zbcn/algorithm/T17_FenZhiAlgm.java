package com.zbcn.algorithm;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * 分而治之 算法
 *
 * @author likun
 * @since 2022/8/9 16:44
 */
public class T17_FenZhiAlgm {


    private static int num = 0;

    /**
     * 如何编程求出一组数据的有序对个数或者逆序对个数呢？ -分而治之 算法
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] a = new int[]{2, 4, 3, 1, 5};
        T17_FenZhiAlgm fenZhiAlgm = new T17_FenZhiAlgm();
        int count = fenZhiAlgm.count(a, a.length);
        System.out.println(count);
    }


    public int count(int[] a, int n) {
        num = 0;
        mergeSortCounting(a, 0, n - 1);
        return num;
    }

    /**
     * 并轨计数
     *
     * @param a 数组
     * @param p 起始位置
     * @param r 结束位置
     */
    private void mergeSortCounting(int[] a, int p, int r) {

        if (p >= r) {
            return;
        }
        int q = (p + r) / 2;
        mergeSortCounting(a, p, q);
        mergeSortCounting(a, q + 1, r);
        merge(a, p, q, r);

    }

    /**
     * 最终的合并操作
     *
     * @param a
     * @param p 起始位置
     * @param q 中间位置
     * @param r 结束位置
     */
    private void merge(int[] a, int p, int q, int r) {

        int i = p, j = q + 1, k = 0;
        int[] tmp = new int[r - p + 1];
        // 循环起始位置和中间位置开始的两个数组
        while (i <= q && j <= r) {
            if (a[i] <= a[j]) {
                tmp[k++] = a[i++];
            } else {
                // 统计 p-q 之间，比 a[j] 大的元素个数
                num += (q - i + 1);
                tmp[k++] = a[j++];
            }
        }
        //处理剩下的
        while( i <= q){
            tmp[k++] = a[i++];
        }
        //处理剩下的
        while(j < r){
            tmp[k++] = a[j++];
        }
        // 从 tmp 拷贝回 a
        for (i=0;i < r-p;i++){
            a[p+i] = tmp[i];
        }

    }


    /**
     * 二维平面上有 n 个点，如何快速计算出两个距离最近的点对
     */
    static class  Test1{

    }


    /**
     * 有两个 n*n 的矩阵 A，B，如何快速求解两个矩阵的乘积 C=A*B
     *
     * @author likun
     * @since 2022/8/10 17:14
     */
    static class RecentPoint{

        List<Point> sortByXPoints = Lists.newArrayList();
        List<Point> sortByYPoints  = Lists.newArrayList();

        private int getDistance(Point p1, Point p2){
            double xDis = Math.pow(p1.x - p2.x,2);
            double yDis = Math.pow(p1.y - p2.y,2);
            return (int)Math.sqrt(xDis + yDis);
        }

        private int findRecentPoint(List<Point> data){
            //可以采用快速排序算法，时间复杂度为O(nlogn)
            this.sortByXPoints = sortedByx(data);
            this.sortByYPoints = sortedByy(data);
            return findRecentPoint(0, data.size());
        }

        private int findRecentPoint(int p, int q) {
            // 区域内只有两对节点
            if ((q - p)<=1 ){
                return getDistance(sortByXPoints.get(p),sortByYPoints.get(q));
            }
            int middle = (int)Math.floor((p+q)/2);
            int ld = findRecentPoint(p, middle);
            int rd = findRecentPoint(middle+1, q);
            int d = Math.min(ld,rd);
            // 中心点
            int e = this.sortByXPoints.get(middle).x + (sortByXPoints.get(middle + 1).x - sortByXPoints.get(middle).x)/2;

            //左边长
             int leftEdg =e - d;
             // 右边长
            int rightEdg = e + d;
            //接下来我们检查已 X 轴坐标 e 为中心点 从 e - d 开始 e + d 结束的带状区域内去检测最近点
            //我们从中筛选所有带状区域内的点，并按照 Y坐标 的递增排序进行排序
            List<Point> insidePoint = Lists.newArrayList();
            for (Point point : sortByYPoints){
                if (point.x > leftEdg && point.x < rightEdg){
                    insidePoint.add(point);
                }
            }
            // 开始对比节点,寻找是否比d更短
            for (int i = 0; i <insidePoint.size(); i++){

                // todo 该处的方法有问题
                for (int j = 0; j < insidePoint.size(); j++){
                    if (i + j > insidePoint.size()){
                        break;
                    }
                    int dis = getDistance(insidePoint.get(i),insidePoint.get(i+j));
                    if (dis < d){
                        d = dis;
                    }
                }
            }
            return d;
        }

        private List<Point> sortedByy(List<Point> data) {
            quickSortx(data,0, data.size());
            return data;
        }

        private List<Point> sortedByx(List<Point> data) {
            quickSorty(data,0, data.size());
            return data;
        }

        private static void quickSorty(List<Point> data,int start, int end) {
            if (start >= end){
                return;
            }
            int left = start;
            int right = end;
            Point temp  = data.get(left);
            int key = temp.y;
            //当左边位置小于右边的元素时
            while (left < right){
                // 从右边开始找，找到一个比基准小的元素
                while (left < right && data.get(right).y >= key){
                    right--;
                }
                //找到右侧比基准小的元素
                data.set(left, data.get(right));
                //从左边开始找，找到第一个比基准大的元素
                while (left < right && data.get(left).y <= key){
                    left++;
                }
                //将找到的值放到 右侧位置
                data.set(right,data.get(left));
            }
            // 基准值归位
            data.set(left,temp);
            //对基准左边额元素进行排序
            quickSorty(data, start, left-1);
            // 对基准右边的值进行排序
            quickSorty(data, right+1, end);
        }

        private static void quickSortx(List<Point> data,int start, int end) {
            if (start >= end){
                return;
            }
            int left = start;
            int right = end;
            Point temp  = data.get(left);
            int key = temp.x;
            //当左边位置小于右边的元素时
            while (left < right){
                // 从右边开始找，找到一个比基准小的元素
                while (left < right && data.get(right).x >= key){
                    right--;
                }
                //找到右侧比基准小的元素
                data.set(left, data.get(right));
                //从左边开始找，找到第一个比基准大的元素
                while (left < right && data.get(left).x <= key){
                    left++;
                }
                //将找到的值放到 右侧位置
                data.set(right,data.get(left));
            }
            // 基准值归位
            data.set(left,temp);
            //对基准左边额元素进行排序
            quickSortx(data, start, left-1);
            // 对基准右边的值进行排序
            quickSortx(data, right+1, end);
        }
    }

    @Data
    static class Point{

        private int x;

        private int y;
    }
}
