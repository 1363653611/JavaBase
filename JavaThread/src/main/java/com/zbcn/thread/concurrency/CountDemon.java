package com.zbcn.thread.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@ThreadSafe
@Slf4j
public class CountDemon{

    private final static int  threadLocal = 200;

    private final static int clientLocal = 5000;

    private static volatile  long count;


    public static void main(String[] args) {

        ExecutorService executor = Executors.newCachedThreadPool();
        final  CountDownLatch latch = new CountDownLatch(clientLocal);
        //信号量，可以起控制线程数量的作用，没200 个一起执行
        final Semaphore semaphore = new Semaphore(threadLocal);

        for (int i = 0; i < clientLocal; i++) {

            executor.submit(() -> {
                try {
                    semaphore.acquire();
                    count();
                    latch.countDown();
                    semaphore.release();
                } catch (  InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        try {
            //等待计算完成
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("计数："+ count);
        executor.shutdown();

    }

    private static synchronized void count(){
        count ++;
    }
}
