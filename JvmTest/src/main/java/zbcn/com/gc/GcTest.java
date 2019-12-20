package zbcn.com.gc;
/**
 *  @title GcTest
 *  @Description 测试 垃圾回收机制
 *  @author zbcn8
 *  @Date 2019/12/2 14:33
 */
public class GcTest {

	/**
	 * config 配置:-XX:+PrintGCDetails
	 * @param args
	 */
	public static void main(String[] args) {
		gc1();
	}

	private static void gc1(){
		byte[] allocation1;
		byte[] allocation2;
		allocation1 = new byte[30900*1024];
	}


}
