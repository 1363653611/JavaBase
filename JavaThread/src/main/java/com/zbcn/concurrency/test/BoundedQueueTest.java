package com.zbcn.concurrency.test;


import com.zbcn.concurrency.example.queue.BoundedQueue;

/**
 * @ClassName: BoundedQueueTest
 * @Description: 有界队列测试
 * @author Administrator
 * @date 2019-07-22 10:31
 *
 */
public class BoundedQueueTest {

	
	public static void main(String[] args) {
		BoundedQueue<String> boundedQueue = new BoundedQueue<>(5);
		try {
			boundedQueue.add("test");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i<= 10; i++) {
			RemoveRunner removeRunner = new RemoveRunner(boundedQueue);
			Thread removeThread = new Thread(removeRunner);
			removeThread.start();
		}
		
		
		for(int i = 0; i<= 10; i++) {
			AddRunner addRunner = new AddRunner(boundedQueue);
			Thread addThread = new Thread(addRunner);
			addThread.start();
		}
		
		
	}
	
	static class AddRunner implements Runnable{

		private BoundedQueue<String> queue;
		
		public AddRunner(BoundedQueue<String> queue) {
			super();
			this.queue = queue;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				queue.add("aa");
				System.out.println("添加"+ queue);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	static class RemoveRunner implements Runnable{

		private BoundedQueue<String> queue;
		
		public RemoveRunner(BoundedQueue<String> queue) {
			super();
			this.queue = queue;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				queue.romove();
				System.out.println("移除后" +queue);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}
