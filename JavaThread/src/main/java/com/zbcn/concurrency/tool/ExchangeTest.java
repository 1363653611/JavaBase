package com.zbcn.concurrency.tool;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: ExchangeTest
 * @Description: 数据交换测试
 * @author Administrator
 * @date 2019-07-31 19:30
 *
 */
public class ExchangeTest {

	private static final Exchanger<String> exchanger = new Exchanger<String>();
	
	private static final ExecutorService executor = Executors.newFixedThreadPool(2);
	
	public static void main(String[] args) {
		executor.execute(new Runnable() {
			
			@Override
			public void run() {
				String A = "银行流水A";
				try {
					Thread.sleep(2000);
					String exchange = exchanger.exchange(A);
					System.out.println("A 录入的是：" + A + "; B录入的是：" + exchange);
					//System.out.println(exchange);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		executor.execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String B = "银行流水B";
				try {
					String exchange = exchanger.exchange(B);
					System.out.println("A 录入的是：" + exchange + "; B录入的是：" + B);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
			}
		});
		
		executor.shutdown();
	}
}
