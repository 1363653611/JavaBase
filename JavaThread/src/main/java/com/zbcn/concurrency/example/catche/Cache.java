package com.zbcn.concurrency.example.catche;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName: Cache
 * @Description: 利用读写锁做缓存
 * @author Administrator
 * @date 2019-07-19 09:50
 *
 */
public class Cache {

	static Map<String, Object> map = new HashMap<>();

	static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	// 读锁
	static Lock rl = rwl.readLock();
	// 写锁
	static Lock wl = rwl.writeLock();

	// 依据key获取value
	public static final Object get(String key) {

		rl.lock();
		try {
			return map.get(key);
		} finally {
			rl.unlock();
		}
	}

	// 向缓存中插入数据
	public static final Object set(String key, Object value) {

		wl.lock();

		try {
			return map.put(key, value);
		} finally {
			wl.unlock();
		}
	}

	// 清空所有的内容
	public static final void clear() {
		wl.lock();
		try {
			map.clear();
		} finally {
			wl.unlock();
		}
	}
}
