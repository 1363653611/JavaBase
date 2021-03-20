package com.zbcn.thread.concurrency.blockqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 */
public class BlockQueueUserDemon {

    public static void main(String[] args) {
        ArrayBlockingQueue queue = new ArrayBlockingQueue<String>(5);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(producer);
        executor.execute(consumer);
        executor.shutdown();
    }
}

class Producer implements Runnable{

    private BlockingQueue<String> queue;

    public Producer(){};
    public Producer(BlockingQueue queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("producer produce 。。。 ");
        queue.add("product");
    }
}

class Consumer implements Runnable {
    private BlockingQueue<String> queue;

    public Consumer(){};
    public Consumer(BlockingQueue queue){
        this.queue = queue;
    }
    @Override
    public void run() {
        try {
            System.out.println("consumer waiting");
            //该方法会阻塞，直到能获取到值
            String take = queue.take();
            System.out.println("consumer take ->"+ take);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
