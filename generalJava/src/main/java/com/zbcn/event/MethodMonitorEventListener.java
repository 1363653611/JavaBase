package com.zbcn.event;

import java.util.EventListener;

/**
 *  事件监听器接口
 *  <br/>
 *  @author zbcn8
 *  @since  2020/9/2 15:34
 */
public interface MethodMonitorEventListener extends EventListener {

    // 处理方法执行之前发布的事件
    public void onMethodBegin(MethodMonitorEvent event);
    // 处理方法结束时发布的事件
    public void onMethodEnd(MethodMonitorEvent event);
}
