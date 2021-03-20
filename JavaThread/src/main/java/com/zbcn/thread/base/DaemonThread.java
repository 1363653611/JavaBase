package com.zbcn.thread.base;

/**
 * 守护线程
 *
 * @author Administrator
 * @date 2018/11/8 18:40
 */
public class DaemonThread {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        //将myThread线程设置为守护线程
        myThread.setDaemon(true);
        myThread.start();
        Thread.sleep(5000);
        System.out.println("我离开thread对象也不再打印了，也就是停止了！");
    }

    static class MyThread extends Thread {
        private int i = 0;

        @Override
        public void run() {
            try {
                while (true) {
                    i++;
                    System.out.println("i=" + (i));
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
