package com.zbcn.concurrency.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: Profiler
 * @Description: threadlocal 类测试
 * @author Administrator
 * @date 2019-06-27 11:47
 *
 */
public class Profiler {
	//第一次get()方法调用时会进行初始化（如果set方法没有调用），每个线程会调用一次
	private static final ThreadLocal<Long> timer = new ThreadLocal<Long>() {
		protected Long initialValue() {
			return System.currentTimeMillis();
		}
	};
	
	public static final void begin() {
		timer.set(System.currentTimeMillis());
	}
	
	public static final Long end() {
		return System.currentTimeMillis() - timer.get();
	}
	
	public static void main(String[] args) throws Exception {
		Profiler.begin();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Cost: " + Profiler.end() + " mills");
	}
}
