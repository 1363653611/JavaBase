package com.zbcn.GOF.state.concrete;

import com.zbcn.GOF.state.framework.Context;
import com.zbcn.GOF.state.framework.Status;

/**
 * 晚上的状态
 */
public class NightStatus implements Status {


    private static Status status = new NightStatus();

    private NightStatus() {
    }

    public static Status getInstance(){
        return status;
    }

    @Override
    public void doClock(Context context, int hour) {
        if(9<=hour && hour <17){
            context.changeStatus(DayStatus.getInstance());
        }
    }

    @Override
    public void doUse(Context context) {
        context.callSecurityCenter("晚上---晚上使用金库");
    }

    @Override
    public void doAlarm(Context context) {
        context.callSecurityCenter("晚上---按下警铃");
    }

    @Override
    public void doPhone(Context context) {
        context.recordLog("晚上---通话录音");
    }

    @Override
    public String toString(){
        return "[晚上]";
    }
}
