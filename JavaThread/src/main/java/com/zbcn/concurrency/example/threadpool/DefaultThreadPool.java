package com.zbcn.concurrency.example.threadpool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @ClassName: DefaultThreadPool
 * @Description: 线程池的默认实现
 * @author Administrator
 * @date 2019-06-27 16:15
 *
 * @param <Job>
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {
	
	/**
	 * 线程池最大限制数量
	 */
	private static final int MAX_WORKS_NUMBERS = 10;
	
	/**
	 * 线程池默认数量
	 */
	private static final int DEFAULT_WORKS_NUMBERS = 5;
	
	private static final int MIN_WORKS_NUMBERS = 1;
	/**
	 * 工作列表，会向里面插入工作
	 */
	private final  LinkedList<Job> jobs = new LinkedList<>();
	/**
	 * 工作者列表
	 */
	private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());
	
	/**
	 * 工作者线程数量
	 */
	private int workerNum = DEFAULT_WORKS_NUMBERS; 
	
	/**
	 * 线程编号生成
	 */
	private AtomicLong threadNum = new AtomicLong();

	public DefaultThreadPool() {
		// TODO Auto-generated constructor stub
		initializeWokers(DEFAULT_WORKS_NUMBERS);
	}
	
	public DefaultThreadPool(int num) {
		this.workerNum = num > MAX_WORKS_NUMBERS? MAX_WORKS_NUMBERS : (num < MIN_WORKS_NUMBERS? MIN_WORKS_NUMBERS : num);
		initializeWokers(workerNum);
	}
	
	
	/**
	 * @Title: initializeWokers
	 * @Description: 初始化工作线程
	 * @param num
	 */
	private void initializeWokers(int num) {
		// TODO Auto-generated method stub
		for (int i = 0; i <= num; i++) {
			Worker worker = new Worker();
			workers.add(worker);
			Thread thread = new Thread(worker,"ThreadPool-Worker-" + threadNum.incrementAndGet());
			thread.start();
		}
		
	}
	
	
	@Override
	public void execute(Job job) {
		if(job != null) {
			//添加一个工作，然后通知
			synchronized (jobs) {
				jobs.addLast(job);
				jobs.notify();
			}
		}
	}

	@Override
	public void shutdown() {
		//关闭所有的工作者
		for (Worker worker : workers) {
			worker.shutdown();
		}
	}

	@Override
	public void addWorks(int num) {
		synchronized (jobs) {
			//限制新增work 的数量不能超过最大值
			if(num + this.workerNum > MAX_WORKS_NUMBERS) {
				num = MAX_WORKS_NUMBERS - this.workerNum;
			}
			initializeWokers(num);
			this.workerNum += num;
		}
		
	}

	@Override
	public void removeWorks(int num) {
		// TODO Auto-generated method stub
		synchronized (jobs) {
			if(num > this.workerNum) {
				throw new IllegalArgumentException("beyond workNum");
			}
			// 按照给定的数量停止Worker
			int count = 0;
			while(count < num) {
				Worker worker = workers.get(count);
				if(workers.remove(worker)) {
					worker.shutdown();
					count++;
				}
			}
			this.workerNum -= count;
		}
	}

	@Override
	public int getJobSize() {
		// TODO Auto-generated method stub
		return jobs.size();
	}
	
	/**
	 * @ClassName: Worker
	 * @Description: 工作者，负责消费任务
	 * @author Administrator
	 * @date 2019-06-27 16:23
	 *
	 */
	class Worker implements Runnable {
		//是否工作
		private volatile boolean running = true;
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(running) {
				Job job = null;
				
				synchronized (jobs) {
					while(jobs.isEmpty()) {
						try {
							jobs.wait();
						} catch (InterruptedException e) {
							// 感知到外部的对work的中断操作
							Thread.currentThread().interrupt();
							return;
						}
					}
					//如果不为空，则取出一个
					job = jobs.removeFirst();
				}
				
				if(job != null) {
					try {
						job.run();
					} catch (Exception e) {
						//忽略job 中的exception
					}
				}
			}
		}
		
		/**
		 * @Title: shutdown
		 * @Description: 停止线程
		 */
		public void shutdown() {
			running = false;
		}
		
	}

}
