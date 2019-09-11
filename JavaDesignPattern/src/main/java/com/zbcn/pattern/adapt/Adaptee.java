package com.zbcn.pattern.adapt;

/**        
 * Title: Adaptee.java8
 * <p>    
 * Description: 已存在的，具有特殊功能，但不符合我们既有接口标准的类
 * @author likun       
 * @created 2018-3-15 下午5:08:41
 * @version V1.0
 */ 
public class Adaptee {
	
	public void specificRequest(){
		System.out.println("被适配类具有 特殊功能..."); 
	}
}
