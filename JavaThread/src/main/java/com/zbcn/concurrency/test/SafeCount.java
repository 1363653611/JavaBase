package com.zbcn.concurrency.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  计数器
 *  <br/>
 *  @author zbcn8
 *  @since  2020/10/28 9:27
 */
public class SafeCount {

    /**
     * 线程安全计数
     */
    public static AtomicInteger  atomI = new AtomicInteger();

    /**
     * 非线程安全
     */
    private static int i = 0;

    /**
     * 利用自旋操CAS作+1
     */
    public static void safeCount(){
        for (;;){
            int i = atomI.get();
            boolean succ = atomI.compareAndSet(i, i++);
            if(succ){
                break;
            }
        }
    }


    /**
     * 非线程安全
     */
    public static void count(){
        i++;
    }


    public static void main(String[] args) {
        final SafeCount cas = new SafeCount();
        List<Thread> ts = new ArrayList<Thread>(600);
        long start = System.currentTimeMillis();
        for (int j = 0; j < 100; j++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        cas.count();
                        cas.safeCount();
                    }
                }
            });
            ts.add(t);
        }
        for (Thread t : ts) {
            t.start();
        }
        // 等待所有线程执行完成
        for (Thread t : ts) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(cas.i);
        System.out.println(cas.atomI.get());
        System.out.println(System.currentTimeMillis() - start);
    }

}
