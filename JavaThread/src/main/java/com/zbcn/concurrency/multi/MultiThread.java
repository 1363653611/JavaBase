package com.zbcn.concurrency.multi;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @ClassName: MultiThread
 * @Description: 多线程系列
 * @author Administrator
 * @date 2019-06-26 17:02
 *
 */
public class MultiThread {

	public static void main(String[] args) {
		//获取java线程管理bean
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		//不需要获取同步的monitor 和 synchronizer信息，仅获取线程和线程堆信息
		ThreadInfo[] dumpAllThreads = threadMXBean.dumpAllThreads(false, false);
		
		for (ThreadInfo threadInfo: dumpAllThreads) {
			System.out.println("[" +threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
		}
	}
}
