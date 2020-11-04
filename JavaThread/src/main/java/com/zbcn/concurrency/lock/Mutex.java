package com.zbcn.concurrency.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @ClassName: Mutex
 * @Description: 自定义锁组件（一个互斥锁）
 * @author Administrator
 * @date 2019-07-02 15:13
 *
 */
public class Mutex implements Lock {
	
	//内部类：自定义同步器
	private static class Sync extends AbstractQueuedSynchronizer {
		//判断锁是否处于占用状态
		protected boolean isHeldExclusively() {
			return getState() == 1;
		}
		
		//获取锁：当前状态为0的时候获取锁
		protected boolean tryAcquire(int acquires) {
			if(compareAndSetState(0, acquires)) {
				setExclusiveOwnerThread(Thread.currentThread());
				return true;
			}
			return false;
		}
		
		//释放锁
		protected boolean tryRelease(int release) {
			
			if(getState() == 0) {
				throw new IllegalMonitorStateException();
			}
			setExclusiveOwnerThread(null);
			setState(0);
			return true;
		}
		
		// 返回一个Condition，每个condition都包含了一个condition队列
		Condition newCondition() {
			return new ConditionObject();
		}
		
		
	}
	
	private final Sync sync = new Sync(); 
	@Override
	public void lock() {
		sync.acquire(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireInterruptibly(1);
	}

	@Override
	public boolean tryLock() {
		return sync.tryAcquire(1);
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return sync.tryAcquireNanos(1, unit.toNanos(time));
	}

	@Override
	public void unlock() {
		// TODO Auto-generated method stub
		sync.release(1);

	}

	@Override
	public Condition newCondition() {
		return sync.newCondition();
	}
	
	/**
	 * @Title: hasQueuedThreads
	 * @Description: 队列中是否有线程在等待获取
	 * @return
	 */
	public boolean hasQueuedThreads() { 
		return sync.hasQueuedThreads(); 
	}

}
