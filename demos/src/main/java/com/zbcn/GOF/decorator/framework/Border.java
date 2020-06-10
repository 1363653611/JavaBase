package com.zbcn.GOF.decorator.framework;

/**
 *  @title Border
 *  @Description 抽象装饰类 装饰者的框架
 *  @author zbcn8
 *  @Date 2020/6/10 17:57
 */
public abstract class Border extends Display {

    protected Display display;

    public Border(Display display) {
        this.display = display;
    }
}
