package com.zbcn.common.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  管道流：必须在多线程下使用，单线程会造成死锁
 *  <br/>
 *  @author zbcn8
 *  @since  2020/9/29 14:14
 */
public class PipedStreamDemo {


    private static ExecutorService executor = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws IOException {
        //单线程
        //singleThreadDemo();
        //多线程
        multiThread();
    }

    private static void multiThread() throws IOException {

        PipedOutputStream pos = new PipedOutputStream();
        PipedInputStream pis = new PipedInputStream(pos);
        //读取字节
        byte[] r = new byte[30];
        //写
        byte[] w = new byte[1024];
        //读取
        executor.execute(()->{
            try {
                int i = 1;
                while (i<200){
                    int j =0;
                    j= pis.read(r,0,30);
                    System.out.println("第"+i+"次读到："+j+"个字节");
                    i++;
                    Thread.sleep(new Random().nextInt(100));
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        //写入
        executor.execute(() ->{
            try {
                int i = 1;
                while (i<200){
                    Arrays.fill(w,(byte)1);
                    pos.write(w,0,1000);
                    System.out.println("第"+i+"次写入"+1000+"个字节,可读:"+pis.available());
                    Thread.sleep(new Random().nextInt(100));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                try {
                    pos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        executor.shutdown();
    }

    // 由于 读取和写入都是使用 PipedInputStream 对象锁，所以回出现死锁现象
    private static void singleThreadDemo() throws IOException {

        PipedOutputStream pos = new PipedOutputStream();
        PipedInputStream pis = new PipedInputStream(pos);
        //读取字节
        byte[] r = new byte[30];
        //写
        byte[] w = new byte[1024];

        Thread thread = new Thread(() -> {
            try {
                int i = 1;
                while (i<200){
                    Arrays.fill(w,(byte)1);
                    pos.write(w,0,1000);
                    System.out.println("第"+i+"次写入"+1000+"个字节,可读:"+pis.available());
                    int j =0;
                    j= pis.read(r,0,30);
                    System.out.println("第"+i+"次读到："+j+"个字节");
                    i++;
                    Thread.sleep(new Random().nextInt(100));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
}
