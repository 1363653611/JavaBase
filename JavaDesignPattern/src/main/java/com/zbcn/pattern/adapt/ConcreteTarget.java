package com.zbcn.pattern.adapt;

/**        
 * Title: ConcreteTarget.java
 * <p>    
 * Description: 具体的目标类，只提供普通的功能
 * @author likun       
 * @created 2018-3-15 下午5:07:29
 * @version V1.0
 */ 
public class ConcreteTarget implements Target {

	public void request() {
		System.out.println("普通类 具有 普通功能...");
	}

}
