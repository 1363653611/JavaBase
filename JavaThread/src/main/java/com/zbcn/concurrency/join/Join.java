package com.zbcn.concurrency.join;

import java.util.concurrent.TimeUnit;

public class Join {

	public static void main(String[] args) throws InterruptedException {
		Thread previous = Thread.currentThread(); 
		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread(new Deamon(previous),String.valueOf(i));
			thread.start();
			previous = thread;
		}
		TimeUnit.SECONDS.sleep(5);
		System.out.println(Thread.currentThread().getName() + " terminate.");
	}
	
	static class Deamon implements Runnable{

		private Thread thread;
		
		public Deamon(Thread thread) {
			super();
			this.thread = thread;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " terminate.");
		}
		
	}
	
}
