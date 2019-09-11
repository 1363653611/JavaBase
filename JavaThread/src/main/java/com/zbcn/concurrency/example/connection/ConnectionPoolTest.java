package com.zbcn.concurrency.example.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: ConnectionPoolTest
 * @Description: 测试
 * @author Administrator
 * @date 2019-06-27 14:20
 *
 */
public class ConnectionPoolTest {
	//创建10个线程池
	static ConnectionPool pool = new ConnectionPool(10);
	//保证所有的connection 能够同时开始
	static CountDownLatch start = new CountDownLatch(1);
	// main线程将会等待所有ConnectionRunner结束后才能继续执行
	static CountDownLatch end;
	
	public static void main(String[] args) throws InterruptedException {
		// 线程数量，可以修改线程数量进行观察
		int threadCount = 10;
		end =  new CountDownLatch(threadCount);
		int count = 20;
		AtomicInteger got = new AtomicInteger();
		AtomicInteger notGot = new AtomicInteger();
		for (int i = 0; i < threadCount; i++) {
			Thread thread = new Thread(new ConnetionRunner(count,got,notGot),"ConnectionRunner");
			thread.start();
		}
		//控制mills 的run方法全部执行
		start.countDown();
		//控制main线程等待 run方法执行完
		end.await();
		System.out.println("total invoke: " + (threadCount * count));
		System.out.println("got connection: " + got);
		System.out.println("not got connection " + notGot);
	}
	
	static class ConnetionRunner implements Runnable{

		int count;
		AtomicInteger got;
		AtomicInteger notGot;
		public ConnetionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
			this.count = count;
			this.got = got;
			this.notGot = notGot;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				start.await();
			}catch (Exception e) {
				// TODO: handle exception
			}
			while(count > 0) {
				// 从线程池中获取连接，如果1000ms内无法获取到，将会返回null
				// 分别统计连接获取的数量got和未获取到的数量notGot
				try {
					Connection connection = pool.fetchConnection(1000);
					if(connection != null) {
						try {
							connection.createStatement();
							connection.commit();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}finally {
							pool.releaseConnection(connection);
							got.incrementAndGet();
						}
					}else {
						notGot.incrementAndGet();
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					count --;
				}
			}
			end.countDown();
		}
		
	}
}
