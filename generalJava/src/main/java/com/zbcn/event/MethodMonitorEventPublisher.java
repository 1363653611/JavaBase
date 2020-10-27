package com.zbcn.event;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *  事件发布者
 *  <br/>
 *  @author zbcn8
 *  @since  2020/9/2 15:39
 */
public class MethodMonitorEventPublisher {

    private final String begin = "begin";

    private final String end = "end";

    private List<MethodMonitorEventListener> listeners = new ArrayList<>();

    /**
     * 发布事件
     * @throws InterruptedException
     */
    public void methodMonitor() throws InterruptedException {
        MethodMonitorEvent eventObject = new MethodMonitorEvent(this);
        publishEvent(begin,eventObject);
        // 模拟方法执行：休眠5秒钟
        TimeUnit.SECONDS.sleep(5);
        publishEvent(end,eventObject);
    }

    private void publishEvent(String status,MethodMonitorEvent event) {
        // 避免在事件处理期间，监听器被移除，这里为了安全做一个复制操作
        List<MethodMonitorEventListener> copyListeners = new ArrayList<>(listeners);
        for (MethodMonitorEventListener listener : copyListeners) {
            if (begin.equals(status)) {
                listener.onMethodBegin(event);
            } else {
                listener.onMethodEnd(event);
            }
        }
    }


    // 省略实现
    public void addEventListener(MethodMonitorEventListener listener) {
        listeners.add(listener);
    }
    public void removeEventListener(MethodMonitorEventListener listener) {}
    public void removeAllListeners() {}

    public static void main(String[] args) throws InterruptedException {
        MethodMonitorEventPublisher publisher = new MethodMonitorEventPublisher();
        publisher.addEventListener(new AbstractMethodMonitorEventListener());
        publisher.methodMonitor();
    }
}
