package com.zbcn.concurrency.executor.futuretask;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName: FutureTaskDeamon2
 * @Description: FutureTask在高并发环境下确保任务只执行一次
 * @author Administrator
 * @date 2019-08-06 19:14
 *
 */
public class FutureTaskDeamon2<T> {

	private  final ConcurrentMap<String, FutureTask<String>> connectionPool = new ConcurrentHashMap<String, FutureTask<String>>();
	
	public static void main(String[] args) {
		FutureTaskDeamon2<String> futureTaskDeamon2 = new FutureTaskDeamon2<String>();
		try {
			String t = futureTaskDeamon2.getT("aaa");
			System.out.println("最终结果：" + t);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getT(String key) throws InterruptedException, ExecutionException {
		FutureTask<String> futureTask = connectionPool.get(key);
		if(Objects.nonNull(futureTask)) {
			return futureTask.get();
		} else {
			Callable<String> callable = new Callable<String>() {
				
				private String a = "测试";
				@Override
				public String call() throws Exception {
					// TODO Auto-generated method stub
					System.out.println("我看看测试。。。");
					return a;
				}
			};
			
			FutureTask<String> newTask = new FutureTask<>(callable);
			futureTask = connectionPool.putIfAbsent(key, newTask);
			
			if(Objects.isNull(futureTask)) {
				futureTask = newTask;  
				futureTask.run();  
			}
		}
		return futureTask.get();
	}
	
	
}
