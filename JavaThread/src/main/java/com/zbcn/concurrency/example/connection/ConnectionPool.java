package com.zbcn.concurrency.example.connection;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName: ConnectionPool
 * @Description: 数据库链接池
 * @author Administrator
 * @date 2019-06-27 13:47
 *
 */
public class ConnectionPool {
	/**
	 * 数据库链接池
	 */
	private LinkedList<Connection> pool = new LinkedList<>();
	
	public ConnectionPool(int initialSize) {
		if(initialSize > 0) {
			for (int i = 0; i <= initialSize; i++) {
				pool.addLast(ConnectionDriver.creationConnection());
			}
		}
	}
	
	//释放连接池
	public void releaseConnection(Connection connection) {
		if(connection != null) {
			synchronized (pool) {
				pool.addLast(connection);
				//连接释放后需要进行通知，这样其他消费者能够感知到连接池中已经归还了一个连接
				pool.notifyAll();
			}
		}
	}
	
	// 在mills内无法获取到连接，将会返回null
	public Connection fetchConnection(long mills) throws InterruptedException {
		synchronized (pool) {
			if(mills <= 0) {
				while(pool.isEmpty()) {
					pool.wait();
				}
				//正向逻辑
				return pool.removeFirst();
			}else {
				long future = System.currentTimeMillis() - mills;
				long remaining = mills;
				while(pool.isEmpty() && remaining > 0) {
					pool.wait(mills);
					remaining = future - System.currentTimeMillis();
				}
				//正向逻辑
				Connection connection = null;
				if(!pool.isEmpty()) {
					connection = pool.removeFirst();
				}
				return connection;
			}
		}
	}
}
