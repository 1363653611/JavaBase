package com.zbcn.thread.synchronize;

/**
 * 重入锁测试：在同一个线程中，锁可以多次获得，即加锁的方法可以重复调用。
 *
 * @author Administrator
 * @date 2018/11/8 19:55
 */
public class Reenter {

     public synchronized void service1() {
        System.out.println("service1");
        service2();
    }

     public synchronized void service2() {
        System.out.println("service2");
        service3();
    }

     public synchronized void service3() {
        System.out.println("service3");
    }

    static class MyThread_1 extends Thread {
        @Override
        public void run() {
            Reenter service = new Reenter();
            service.service1();
        }

    }

    public static void main(String[] args) {
        MyThread_1 t = new MyThread_1();
        t.start();
    }


}
