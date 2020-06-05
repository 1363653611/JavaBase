package com.zbcn.GOF.iterator.interfaces;

/**
 * 定义创建 iterator 接口的创建器
 */
public interface Aggregator {

    /**
     * 获取迭代器对象
     * @return
     */
    Iterator iterator();
}
