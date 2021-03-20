package com.zbcn.concurrency.notify;

import com.zbcn.concurrency.state.SleepUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: WaitNotify
 * @Description: 等待/通知线程
 * @author Administrator
 * @date 2019-06-27 10:13
 *
 */
public class WaitNotify {

	static boolean flag = true;
	static Object lock = new Object();

	public static void main(String[] args) throws InterruptedException {
		Thread waitThread = new Thread(new Wait(), "WaitThread");
		waitThread.start();
		TimeUnit.SECONDS.sleep(1L);
		Thread notifyThread = new Thread(new Notify(), "NotifyThread");
		notifyThread.start();
	}

	static class Wait implements Runnable {

		@Override
		public void run() {
			// 加锁，拥有lock 的monitor
			synchronized (lock) {
				while (flag) {
					try {
						System.out.println(Thread.currentThread() + " flag is true. wait @ "
								+ new SimpleDateFormat("HH:mm:ss").format(new Date()));
						//调用wait 方法后，回释放到当前获取盗的锁，
						// 直到 执行notify 方法的线程释放了线程后，重新获取该锁
						lock.wait();
					} catch (InterruptedException e) {
//
					}
				}
				System.out.println("flag is false. running @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
			}

		}
	}

	//notify()或notifyAll()方法调用后，等待线程依旧不会从wait()返回，需要调用notify()或
	//notifAll()的线程释放锁之后，等待线程才有机会从wait()返回。
	static class Notify implements Runnable {

		@Override
		public void run() {
			// 加锁，拥有lock的Monitor
			synchronized (lock) {
				// 获取lock的锁，然后进行通知，通知时不会释放lock的锁，
				// 直到当前线程释放了lock后，WaitThread才能从wait方法中返回
				System.out.println(Thread.currentThread() + " hold lock. notify @ "
						+ new SimpleDateFormat("HH:mm:ss").format(new Date()));
				lock.notifyAll();
				flag = false;
				SleepUtils.second(5L);
			}
			//再次加锁
			synchronized (lock) {
				System.out.println(Thread.currentThread() + " hold lock again. sleep @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
						SleepUtils.second(5L);
			}
		}
	}
}
