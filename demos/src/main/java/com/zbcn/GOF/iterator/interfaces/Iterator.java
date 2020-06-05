package com.zbcn.GOF.iterator.interfaces;

/**
 *  @title Iterator
 *  @Description 迭代器接口
 *  @author zbcn8
 *  @Date 2020/6/5 16:35
 */
public interface Iterator {

    /**
     * 判断是否有下一个对象
     * @return
     */
    boolean hasNext();

    /**
     * 获取下一个对象
     * @return
     */
    Object next();
}
