package com.zbcn.GOF.state.framework;

/**
 * 负责状态管理和联系报警
 */
public interface Context {

    /**
     * 改变状态
     * @param status
     */
    void changeStatus(Status status);

    /**
     * 联系报警中心
     * @param msg
     */
    void callSecurityCenter(String msg);

    /**
     * 在报警中心留下的记录
     * @param msg
     */
    void recordLog(String msg);
}
