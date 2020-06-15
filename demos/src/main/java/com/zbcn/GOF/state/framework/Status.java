package com.zbcn.GOF.state.framework;

/**
 *  @title Status
 *  @Description 表示状态的接口
 *  @author zbcn8
 *  @Date 2020/6/15 17:28
 */
public interface Status {

    /**
     * 设置时间
     * @param context
     */
    void doClock(Context context,int hour);

    /**
     * 使用金库
     * @param context
     */
    void doUse(Context context);

    /**
     * 按下报警铃
     * @param context
     */
    void doAlarm(Context context);

    /**
     * 正常通话
     * @param context
     */
    void doPhone(Context context);
}
