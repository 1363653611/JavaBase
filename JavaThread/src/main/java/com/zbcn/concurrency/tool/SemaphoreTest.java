package com.zbcn.concurrency.tool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @ClassName: SemaphoreTest
 * @Description: 信号量（流量控制）
 * @author Administrator
 * @date 2019-07-31 16:42
 *
 */
public class SemaphoreTest {

	/**
	 * 线程总数量
	 */
	private static final Integer THREAD_COUNT = 40;
	
	private static  ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
	
	private static Semaphore semaphore = new Semaphore(5);
	
	public static void main(String[] args) {
		for(int i = 0; i<THREAD_COUNT;i++) {
			executorService.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						semaphore.acquire();
						
						System.out.println("do something");
						Thread.sleep(2000);
						semaphore.release();
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// TODO Auto-generated method stub
					
				}
			});
		}
		executorService.shutdown();
	}
	
}
