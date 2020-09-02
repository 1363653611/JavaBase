package com.zbcn.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    private static ExecutorService executor = Executors.newFixedThreadPool(5);

    private static Semaphore semaphore = new Semaphore(2);
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            executor.execute(() ->{
                try {
                    semaphore.acquire();
                    System.out.println("【"+ finalI +"】当前线程开始执行：" + Thread.currentThread().getName());
                    Thread.sleep(500);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("【"+ finalI +"】当前线程结束执行：" + Thread.currentThread().getName());
                semaphore.release();
            });
        }
        executor.shutdown();
    }
}
