package com.zbcn.concurrency.example.connection;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

public class ConnectionDriver {

	static class ConnectionHandler implements InvocationHandler {

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			// TODO Auto-generated method stub
			if(StringUtils.equals(method.getName(), "commit")) {
				TimeUnit.MILLISECONDS.sleep(100);
			}
			return null;
		}
		
	}
	
	//创建一个connection代理，在commit时休眠100毫秒
	public static final Connection creationConnection() {
		return (Connection)Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(), new Class[] {Connection.class}, new ConnectionHandler());
	}
}
