package com.zbcn.pattern.adapt;

/**        
 * Title: Test.java
 * <p>    
 * Description: 测试适配器模式
 * @author likun       
 * @created 2018-3-15 下午5:11:37
 * @version V1.0
 */ 
public class Test {
	public static void main(String[] args) {
		// 使用普通功能类  
        Target concreteTarget = new ConcreteTarget();  
        concreteTarget.request();
        // 使用特殊功能类，即适配类  
        Target adapter = new Adapter();  
        adapter.request(); 
	}
}
