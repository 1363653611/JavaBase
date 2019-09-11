package com.zbcn.concurrency.tool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * @ClassName: CountDownLauchTest
 * @Description: 减计数器控制
 * @author Administrator
 * @date 2019-07-31 19:54
 *
 */
public class CountDownLauchTest {

	private static final CountDownLatch countDownLatch = new CountDownLatch(2);
	
	private static final ExecutorService executor = Executors.newFixedThreadPool(2);
	
	
	public static void main(String[] args) throws InterruptedException {
		executor.execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				countDownLatch.countDown();
				System.out.println(Thread.currentThread().getName() + "线程1执行");
			}
		});
		
		executor.execute(new Runnable() {
			
			@Override
			public void run() {
				countDownLatch.countDown();
				System.out.println(Thread.currentThread().getName() + "线程2执行");
			}
		});
		executor.shutdown();
		countDownLatch.await();
		System.out.println(Thread.currentThread().getName() + " 主线程执行");
	}
}
