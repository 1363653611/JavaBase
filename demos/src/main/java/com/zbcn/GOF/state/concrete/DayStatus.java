package com.zbcn.GOF.state.concrete;

import com.zbcn.GOF.state.framework.Context;
import com.zbcn.GOF.state.framework.Status;

/**
 * 具体状态实现
 */
public class DayStatus implements Status {

    /**
     * 如果每个状态都创建一个 status 对象,浪费时间和内存,所以我们采用 单例模式
     */
    private static DayStatus dayStatus =  new DayStatus();

    private DayStatus() {
    }

    public static Status getInstance(){
        return dayStatus;
    }

    @Override
    public void doClock(Context context, int hour) {
        if(hour < 9 || 17 >= hour){
            context.changeStatus(NightStatus.getInstance());
        }
    }

    @Override
    public void doUse(Context context) {
        context.recordLog("白天--使用金库");
    }

    @Override
    public void doAlarm(Context context) {
        context.callSecurityCenter("白天---按下警玲");
    }

    @Override
    public void doPhone(Context context) {
        context.callSecurityCenter("白天---正常通话,...");
    }

    @Override
    public String toString(){
        return "[白天]";
    }
}
