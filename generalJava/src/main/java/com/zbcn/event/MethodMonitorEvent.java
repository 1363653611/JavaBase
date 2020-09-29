package com.zbcn.event;

import java.util.EventObject;

/**
 *  事件类
 *  <br/>
 *  @author zbcn8
 *  @since  2020/9/2 15:33
 */
public class MethodMonitorEvent extends EventObject {
    // 时间戳，用于记录方法开始执行的时间
    public long timestamp;

    public MethodMonitorEvent(Object source) {
        super(source);
    }
}
