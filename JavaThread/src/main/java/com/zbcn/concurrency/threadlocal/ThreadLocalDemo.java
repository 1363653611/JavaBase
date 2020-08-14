package com.zbcn.concurrency.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Random;

/**
 *  threadLocal 测试
 *  <br/>
 *  @author zbcn8
 *  @since  2020/8/13 13:40
 */
public class ThreadLocalDemo implements Runnable {

    private static final ThreadLocal<SimpleDateFormat>  formatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd HHmm"));

    /**
     * 从输出中可以看出，Thread-0已经改变了formatter的值，但仍然是thread-2默认格式化程序与初始化值相同，其他线程也一样。
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ThreadLocalDemo obj = new ThreadLocalDemo();
        for (int i = 0; i < 10 ; i++) {
            Thread thread = new Thread(obj, " test" + i);
            Thread.sleep(new Random().nextInt(1000));
            thread.start();
        }
    }

    @Override
    public void run() {
        System.out.println("thread name = " + Thread.currentThread().getName() + "default formatter:" + formatter.get().toPattern());
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //修改formatter 中的值
        formatter.set(new SimpleDateFormat());
        System.out.println("Thread Name= "+Thread.currentThread().getName()+" formatter = "+formatter.get().toPattern());
    }
}
