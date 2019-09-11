package com.zbcn.concurrency.executor.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @ClassName: FutureTaskDeamon
 * @Description: futureTask 测试类：执行多任务计算的使用场景
 * @author Administrator
 * @date 2019-08-05 14:58
 *
 */
public class FutureTaskDeamon {

	
	/**
	 * @Title: main
	 * @Description: TODO
	 * @param args 
	 */
	public static void main(String[] args) {
		
		Task task = new Task();//新建异步任务
		FutureTask<Integer> futureTask = new FutureTask<Integer>(task) {
			 @Override
	         protected void done() {
	                try {
	                    System.out.println("future.done():" + get());
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                } catch (ExecutionException e) {
	                    e.printStackTrace();
	                }
	         }
			 
		}; 
		
		// 创建线程池（使用了预定义的配置）
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(futureTask);
    
        try {
        	//该方法是一个阻塞方法
			Integer integer = futureTask.get();
			System.out.println(integer);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        executor.isShutdown();
	}

	
	
	/**
	 * @ClassName: Task
	 * @Description: 带返回值的任务
	 * @author Administrator
	 * @date 2019-08-05 15:03
	 *
	 */
	static class Task implements Callable<Integer> {

		@Override
		public Integer call() throws Exception {
			 int i = 0;
			 for(; i<10; i++) {
				 System.out.println(Thread.currentThread().getName() + "_" + i);
				 Thread.sleep(1000);
			 }
			return i;
		}
		
	}
}
