package com.zbcn.concurrency.tool;

import java.util.Map.Entry;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest implements Runnable {
	
	/**
	 * 创建4个屏障，执行完后执行当前的run 方法
	 */
	private CyclicBarrier c = new CyclicBarrier(4,this);
	
	/**
	 * 假设只有4个sheet，所以创建四个线程
	 */
	private ExecutorService executor = Executors.newFixedThreadPool(4);
	
	/**
	 * 存放计数的求和
	 */
	private ConcurrentMap<String, Integer> map = new ConcurrentHashMap<String, Integer>();
	
	
	public void count() {
		for(int i= 0; i<4; i++) {
			int finalI = i;
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					// 计算每个sheet 的内容的方法开始
					System.out.println(Thread.currentThread().getName() + "开始执行：" + finalI);
					map.put(Thread.currentThread().getName(), 1);
					
					//计算完成插入一个屏障
					try {
						c.await();
					} catch (InterruptedException | BrokenBarrierException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		int result = 0;
		for(Entry<String, Integer> sheet: map.entrySet()) {
			result +=sheet.getValue();
		}
		System.out.println("最终结果：" + result);
		//map.put("result", result);
		executor.shutdown();//关闭线程池
	}
	
	public static void main(String[] args) {
		CyclicBarrierTest backWaterServer = new CyclicBarrierTest();
		backWaterServer.count();

	}

}
