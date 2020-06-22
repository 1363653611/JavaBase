package com.zbcn.GOF.mediator.framework;

/**
 *  @title Mediator
 *  @Description 仲裁者接口
 *  @author zbcn8
 *  @Date 2020/6/11 19:13
 */
public interface Mediator {

    /**
     * 生成mediator 要管理的组员
     */
    public abstract void createColleagues();

    /**
     * 向仲裁者进行汇报
     */
    public abstract void colleagueChanges();
}

