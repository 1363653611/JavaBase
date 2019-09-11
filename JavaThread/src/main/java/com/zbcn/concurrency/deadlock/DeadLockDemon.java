package com.zbcn.concurrency.deadlock;

/**
 * @ClassName: DeadLockDemon
 * @Description: 死锁的例子
 * @author Administrator
 * @date 2019-06-11 19:13
 *
 */
public class DeadLockDemon {
	
	private static String A = "A";
	
	private static String B = "B";
	
	/**
	 * @Title: main
	 * @Description: 避免死锁的方法：
	 * 1.避免一个线程同时获取多个锁
	 * 2.避免一个线程在锁内同时占用多个资源，尽量保证每个锁只占用一个资源。
	 * 3.尝试使用定时锁，使用lock.trylock(timeout)来代替使用内部锁机制
	 * 4.对于数据库锁，加锁和解锁必须在一个数据库链接里，否则会出现解锁失败情况。
	 * @param args
	 */
	public static void main(String[] args) {
		new DeadLockDemon().deadLock();
	}
	
	private void deadLock() {
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				synchronized (A) {
					try {
						System.out.println("t1 runing.......");
						Thread.currentThread().sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					synchronized (B) {
						System.out.println("t1 runing");
					}
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				synchronized (B) {
					System.out.println("t2 runing.......");
					synchronized (A) {
						System.out.println("t2 runing");
					}
				}
				
			}
		});
		
		t1.start();
		t2.start();
	}

}