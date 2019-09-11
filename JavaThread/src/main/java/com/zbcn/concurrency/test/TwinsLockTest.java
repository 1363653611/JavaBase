package com.zbcn.concurrency.test;

import java.util.concurrent.locks.Lock;

import com.zbcn.concurrency.lock.TwinsLock;
import com.zbcn.concurrency.state.SleepUtils;

/**
 * @ClassName: TwinsLockTest
 * @Description: 测试两个线程锁
 * @author Administrator
 * @date 2019-07-14 11:42
 *
 */
public class TwinsLockTest {
	
	public static void main(String[] args) {
		test();
	}

	public static void test() {
		final Lock lock = new TwinsLock();
		class Worker extends Thread {
			public void run() {
				while (true) {
					lock.lock();
					try {
						SleepUtils.second(1L);
						System.out.println(Thread.currentThread().getName());
						SleepUtils.second(1L);
					} finally {
						lock.unlock();
					}
				}
			}
		}
		// 启动10个线程
		for (int i = 0; i < 10; i++) {
			Worker w = new Worker();
			w.setDaemon(true);
			w.start();
		}
		// 每隔1秒换行
		for (int i = 0; i < 10; i++) {
			SleepUtils.second(1L);
			System.out.println();
		}
	}
}
