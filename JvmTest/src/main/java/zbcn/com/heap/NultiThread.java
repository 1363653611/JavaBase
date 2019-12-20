package zbcn.com.heap;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 *  @title NultiThread
 *  @Description Main 线程执行，看似没有其他线程执行，实际上还有其他线程协作\
 * [4]Signal Dispatcher 分发处理发送给JVM 信号的线程
 * [3]Finalizer 调用 finalize 方法的线程
 * [2]Reference Handler 清除reference 的线程
 * [1]main 用户程序入口线程
 *  @author zbcn8
 *  @Date 2019/11/28 9:33
 */
public class NultiThread {

	/**
	 * 使用JMX 查看普通的Java程序包包含哪些线程
	 * @param args
	 */
	public static void main(String[] args) {
		//获取java 的线程管理 MXBean
		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		//不需要获取同步的monitor 和 synchronize 信息，仅获取线程和线程的堆栈信息
		ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
		for (ThreadInfo threadInfo:threadInfos){
			System.out.println("[" + threadInfo.getThreadId() + "]" + threadInfo.getThreadName());
		}

	}
}
