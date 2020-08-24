package com.zbcn.concurrency;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.time.LocalDateTime;
import java.util.concurrent.*;

public class ExecutorPoolTest {

    /**
     * 自定义的拒绝策略,工作队列调用put让其一直等待,直到有可用的容量存放任务.
     */
    private final static RejectedExecutionHandler handler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            if (!executor.isShutdown()) {
                try {
                    System.out.println("开始队列插入"+ LocalDateTime.now());
                    executor.getQueue().put(r);
                    System.out.println("开始队列结束");
                } catch (InterruptedException e) {
                    System.out.println("线程池自定义拒绝策略出错：" + e.toString());
                }
            }
        }
    };

    static ExecutorService pool = new ThreadPoolExecutor(3, 3, 200, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(1),
            new ThreadFactoryBuilder().setNameFormat("saveMongodb-pool-%d").build(), handler);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++){
            int finalI = i;
            System.out.println("第" + i+"任务加入:" + LocalDateTime.now());
            pool.execute(() -> {
                try {
                    Thread.sleep(20000);
                    System.out.println(Thread.currentThread().getName() + "第" + finalI + "执行完成");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        }
        System.out.println("for 循环完成");
    }
}
