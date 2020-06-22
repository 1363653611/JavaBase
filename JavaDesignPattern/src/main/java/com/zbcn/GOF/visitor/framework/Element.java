package com.zbcn.GOF.visitor.framework;

/**
 * 接受访问者访问的接口
 */
public interface Element {

    public  abstract  void accept(Visitor visitor);
}
