package com.zbcn.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁与非公平锁；
 * 公平锁 和 非公平锁。公平锁表示线程获取锁的顺序是按照线程加锁的顺序来分配的，即先来先得的FIFO先进先出顺序。
 * 而非公平锁就是一种获取锁的抢占机制，是随机获取锁的，
 * 和公平锁不一样的就是先来的不一定先的到锁，这样可能造成某些线程一直拿不到锁，结果也就是不公平的了。
 *
 * @author Administrator
 * @date 2018/11/12 20:26
 */
public class FairorNofairLock {

    public static void main(String[] args) {
        //表示是否为公平锁：
        //true为公平锁，false为非公平锁
        final Service service = new Service(true);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("★线程" + Thread.currentThread().getName() +"运行了。");
                service.serviceMethod();
            }
        };

        Thread[] threads = new Thread[10];
        for(int i = 0; i < 10 ; i++){
            threads[i] = new Thread(runnable);

        }
        for(int i = 0; i < 10 ; i++){
            threads[i].start();

        }


    }

    static class Service {
        private Lock lock;

        public Service(boolean isFairor) {
            this.lock = new ReentrantLock(isFairor);
        }

        public void serviceMethod(){
            lock.lock();
            try {
                System.out.println("ThreadName= " + Thread.currentThread().getName() + "获得锁");
            } finally {
                lock.unlock();
            }
        }
    }
}
