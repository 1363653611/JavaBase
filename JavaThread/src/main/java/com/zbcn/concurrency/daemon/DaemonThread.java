package com.zbcn.concurrency.daemon;

import com.zbcn.concurrency.state.SleepUtils;

public class DaemonThread {

	public static void main(String[] args) {
		Thread thread = new Thread(new Daemon(),"daemon_runner");
		thread.setDaemon(true); //在线程启动前设置
		thread.start();
		System.out.println("主线程结束");
	}

	//DAEMON thread 被用做支持性工作，但是当虚拟机退出时，daemon线程中的finally语句块不一定被执行
	// 在使用daemon线程时，不要认为finally中的语句一定会被执行
	static class Daemon implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				SleepUtils.second(100L);
			}finally {
				System.out.println("daemon thread finally run");
			}
			
		}
		}
	}
