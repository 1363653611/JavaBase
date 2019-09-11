package com.zbcn.pattern.strategyandstate.simplestate;

/**        
 * Title: Client.java8
 * <p>    
 * Description: 测试客户端
 * @author likun       
 * @created 2018-3-20 下午4:38:11
 * @version V1.0
 */ 
public class Client {
	public static void main(String[] args) {
		FileSub file = new FileSub();
		file.setState(new BeginState());
		file.submit();
		file.submit();
		file.submit();
	}
}
