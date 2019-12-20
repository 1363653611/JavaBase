package com.zbcn.concurrency.close;

import com.zbcn.concurrency.state.SleepUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *  @title Deprecated
 *  @Description 废弃的终止线程的方法
 *  @author zbcn8
 *  @Date 2019/12/20 11:18
 */
public class Deprecated {

	public static void main(String[] args) throws InterruptedException {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		Thread printThread = new Thread(new Runner(), "PrintThread");
		printThread.setDaemon(true);//主线程停止时停止
		printThread.start();
		TimeUnit.SECONDS.sleep(3L);
		// 将PrintThread进行暂停，输出内容工作停止
		printThread.suspend();
		System.out.println("main suspend PrintThread at " + format.format(new Date()));
		TimeUnit.SECONDS.sleep(3);
		// 将PrintThread进行恢复，输出内容继续
		printThread.resume();
		System.out.println("main resume PrintThread at " + format.format(new Date()));
		TimeUnit.SECONDS.sleep(3);
		// 将PrintThread进行终止，输出内容停止
		printThread.stop();
		System.out.println("main stop PrintThread at " + format.format(new Date()));
		TimeUnit.SECONDS.sleep(3);
	}

	static class Runner implements Runnable {
		@Override
		public void run() {
			DateFormat format = new SimpleDateFormat("HH:mm:ss");
			while (true) {
				System.out.println(Thread.currentThread().getName() + " Run at " +
						format.format(new Date()));
				SleepUtils.second(1L);
			}
		}
	}
}
