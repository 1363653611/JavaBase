package com.zbcn.concurrency.example.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: BoundedQueue
 * @Description: 有界队列
 * @author Administrator
 * @date 2019-07-19 17:21
 *
 */
public class BoundedQueue<T> {
	
	private Object[] items;
	
	/**
	 * 添加的下标
	 */
	private int addIndex;
	
	/**
	 * 删除的下标
	 */
	private int removeIndex;
	
	/**
	 * 数组当前数量
	 */
	private int count;

	private Lock lock = new ReentrantLock();
	
	/**
	 * 不为空condition
	 */
	private Condition notEmpty = lock.newCondition();
	
	/**
	 * 不满的condition
	 */
	private Condition notFull = lock.newCondition();
	
	/**
	 * 创建一个新的实例 BoundedQueue 设置queue的初始化大小
	 *
	 * @param size
	 */
	public BoundedQueue (int size){
		items = new Object[size];
	}
	
	public void add(T t) throws InterruptedException {
		lock.lock();
		try {
			//判断当前的数量和数组的长度是否相等
			//在添加和删除方法中使用while循环而非if判断，目的是防止过早或意外的通知，只有条件符合才能够退出循环
			while(count == items.length) {
				notFull.await();
			}
			//如果添加了
			items[addIndex] = t;
			addIndex = 0;
			++count;
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}
	// 由头部删除一个元素，如果数组空，则删除线程进入等待状态，直到有新添加元素
	@SuppressWarnings("unchecked")
	public T romove() throws InterruptedException {
		lock.lock();
		try {
			//在添加和删除方法中使用while循环而非if判断，目的是防止过早或意外的通知，只有条件符合才能够退出循环
			while(count == 0) {
				notEmpty.await();
			}
			//不为空
			Object x = items[removeIndex];
			if (++removeIndex == items.length) {
				removeIndex = 0;
			}
			--count;
			notFull.signal();
			return (T) x;	
			
		} finally {
			lock.unlock();
		}
	}

}
