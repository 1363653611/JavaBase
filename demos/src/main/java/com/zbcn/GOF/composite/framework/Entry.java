package com.zbcn.GOF.composite.framework;

/**
 *  @title Entry
 *  @Description 表示目录条目的抽象类
 *  @author zbcn8
 *  @Date 2020/6/9 14:26
 */
public abstract class Entry {

    public abstract String getName();

    public  abstract  int getSize();

    public void printList(){
        printList("");
    }

    public abstract void printList(String prefix);
}
