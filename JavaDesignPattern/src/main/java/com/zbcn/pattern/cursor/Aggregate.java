package com.zbcn.pattern.cursor;

/**
 * 抽象聚集角色类
 *
 * @author zbcn
 * @create 2018-05-25 15:38
 **/
public abstract class Aggregate {
    /**
     * 工厂方法，创建相应迭代子对象的接口
     * @return
     */
    public abstract Iterator createIterator();
}
