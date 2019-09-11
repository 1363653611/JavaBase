package com.zbcn.pattern.cursor;

/**
 * 抽象子迭代角色类
 */
public interface Iterator {

    /**
     * 迭代方法：移动到第一个元素
     */
    public void first();

    /**
     * 迭代方法：移动到下一个元素
     */
    public void next();

    /**
     * 迭代方法：是否是最后一个元素
     * @return
     */
    public boolean isDone();

    /**
     * 迭代方法：返回当前元素
     * @return
     */
    public Object currentObject();


}
