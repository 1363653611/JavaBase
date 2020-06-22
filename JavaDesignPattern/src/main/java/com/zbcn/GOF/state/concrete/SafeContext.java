package com.zbcn.GOF.state.concrete;

import com.zbcn.GOF.state.framework.Context;
import com.zbcn.GOF.state.framework.Status;

/**
 *  @title SafeContext
 *  @Description 具体的策略容器
 *  @author zbcn8
 *  @Date 2020/6/15 18:03
 */
public class SafeContext implements Context {

    /**
     * 默认状态是白天
     */
    private Status status = DayStatus.getInstance();

    @Override
    public void changeStatus(Status status) {
        System.out.println("改变状态:" + status);
        this.status = status;
    }

    @Override
    public void callSecurityCenter(String msg) {
        System.out.println("报警:" + msg);
    }

    @Override
    public void recordLog(String msg) {
        System.out.println("留下记录: " + msg);
    }

    public void doing(int i){
       status.doClock(this, i);
       status.doAlarm(this);
       status.doPhone(this);
       status.doUse(this);
    }
}
