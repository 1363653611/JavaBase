package com.zbcn.concurrency.close;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: ShutDown
 * @Description: 线程关闭
 * @author Administrator
 * @date 2019-06-27 09:36
 *
 */
public class ShutDown {
	public static void main(String[] args) throws Exception {
		Thread countThread = new Thread (new Runner(),"one");
		countThread.start();
		TimeUnit.SECONDS.sleep(1L);
		countThread.interrupt();
		Runner two = new Runner();
		countThread = new Thread(two, "CountThread");
		countThread.start();
		// 睡眠1秒，main线程对Runner two进行取消，使CountThread能够感知on为false而结束
		TimeUnit.SECONDS.sleep(1);
		two.cancel();
	}
	
	static class Runner implements Runnable {

		private long i;
		private volatile boolean on = true;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(on && !Thread.currentThread().isInterrupted()) {
				i ++;
			}
			System.out.println("count i=" + i);
		}
		
		public void cancel() {
			this.on = false;
		}
		
	}
}
