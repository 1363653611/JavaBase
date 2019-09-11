package com.zbcn.concurrency.state;

public class ThreadState {

	public static void main(String[] args) {
		new Thread(new TimeWaiting(),"TimeWaitingThread").start();
		new Thread(new Waiting()," WaitingThread").start();
		// 使用两个Blocked线程，一个获取锁成功，另一个被阻塞
		new Thread(new Blocked(), "BlockedThread-1").start();
		new Thread(new Blocked(), "BlockedThread-2").start();
	}

	// 线程不断地睡眠
	static class TimeWaiting implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				SleepUtils.second(100L);
			}
		}

	}

	// 该线程在Waiting.class 实例上等待
	static class Waiting implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				synchronized (Waiting.class) {
					try {
						Waiting.class.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}

	}

	// 该线程在Blocked.class实例上加锁后，不会释放该锁
	static class Blocked implements Runnable {
		public void run() {
			synchronized (Blocked.class) {
				while (true) {
					SleepUtils.second(100L);
				}
			}
		}
	}

}
