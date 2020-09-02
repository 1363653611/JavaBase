package com.zbcn.structure;

/**
 *  优先队列
 *  <br/>
 *  @author zbcn8
 *  @since  2020/8/27 19:56
 */
public class MaxPq<T extends Comparable> {

    /**
     * 存储元素的数组
     */
    private T[] pq;

    /**
     * 当前 Priority Queue 中的元素个数
     */
    private int N = 0;

    public MaxPq(int cap) {
        // 索引 0 不用，所以多分配一个空间
        pq = (T[]) new Comparable[cap + 1];
    }

    /**
     * 返回当前队列中最大元素
     * @return
     */
    public T max(){
        return pq[1];
    }
    /* 插入元素 e */
    public void insert(T e) {
        //.....
    }

    /* 删除并返回当前队列中最大元素 */
    public T delMax() {
        //...
        return null;
    }

    /* 上浮第 k 个元素，以维护最大堆性质 */
    private void swim(int k) {
        //...
    }

    /* 下沉第 k 个元素，以维护最大堆性质 */
    private void sink(int k) {
        //...
    }

    /* 交换数组的两个元素 */
    private void exch(int i, int j) {
        T temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    /* pq[i] 是否比 pq[j] 小？ */
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    /* 还有 left, right, parent 三个方法 */


}
