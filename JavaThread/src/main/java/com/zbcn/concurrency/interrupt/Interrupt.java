package com.zbcn.concurrency.interrupt;

import com.zbcn.concurrency.state.SleepUtils;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: Interrupt
 * @Description: 中断 守护线程
 * @author Administrator
 * @date 2019-08-08 11:52
 *
 */
public class Interrupt {

	public static void main(String[] args) {
		Thread sleep = new Thread(new SleepRunner(),"SleepRunner");
		//sleep.setDaemon(true);
		Thread busy = new Thread(new BusyRunner(),"BusyRunner");
		busy.setDaemon(true); // busy 线程的停止是利用守护线程实现的,及main 线程停止,则busy线程终止
		sleep.start();
		busy.start();
		try {
			TimeUnit.SECONDS.sleep(5L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//打断失败，抛出InterruptedException 异常
		sleep.interrupt();
		busy.interrupt();
		System.out.println("SleepThread interrupted is " + sleep.isInterrupted());
		System.out.println("BusyThread interrupted is " + busy.isInterrupted());
		// 防止sleepThread和busyThread立刻退出
		SleepUtils.second(2L);
	}
	
	static class SleepRunner implements Runnable {

		@Override
		public void run() {
			//Thread.currentThread();
			// TODO Auto-generated method stub
			//SleepUtils.second(100L);
			boolean flag = false;
			while(!flag) {
				boolean interrupted = Thread.currentThread().isInterrupted();
				if(interrupted) {
					flag = true;
				}
				System.out.println("没有被线程中断");
			}
			
			System.out.println("线程被中断");
		}
		
	}
	
	static class BusyRunner implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true) {

			}
		}
		
	}
}
