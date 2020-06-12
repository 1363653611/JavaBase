package com.zbcn.GOF.mediator.framework;

/**
 *  @title Colleague
 *  @Description 向仲裁者汇报的组员接口
 *  @author zbcn8
 *  @Date 2020/6/11 19:17
 */
public interface Colleague {

    void  setMediator(Mediator mediator);

    /**
     * 下达禁用/启用的标志
     * @param enabled
     */
    void setColleagueEnabled(boolean enabled);
}
