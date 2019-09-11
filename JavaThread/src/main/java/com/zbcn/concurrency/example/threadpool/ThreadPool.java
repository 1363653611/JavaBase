package com.zbcn.concurrency.example.threadpool;

/**
 * @ClassName: ThreadPool
 * @Description: 自建一个线程池
 * @author Administrator
 * @date 2019-06-27 16:07
 *
 */
public interface ThreadPool<Job extends Runnable> {
	/**
	 * @Title: execute
	 * @Description: 执行一个job，需要实现runnable
	 * @param job
	 */
	void execute(Job job);
	
	/**
	 * @Title: shutdown
	 * @Description: 关闭线程
	 */
	void shutdown();
	
	/**
	 * @Title: addWorks
	 * @Description: 增加工作线程
	 * @param num
	 */
	void addWorks(int num);
	
	/**
	 * @Title: removeWorks
	 * @Description: 减少工作线程
	 * @param num
	 */
	void removeWorks(int num);
	
	/**
	 * @Title: getJobSize
	 * @Description: 得到正在等待执行的任务数量
	 * @return
	 */
	int getJobSize();

}
