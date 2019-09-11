package com.zbcn.pattern.Propotype;

/**        
 * Title: Client.java
 * <p>    
 * Description: 原型模式的测试
 * @author likun       
 * @created 2018-3-16 上午10:27:33
 * @version V1.0
 */ 
public class Client {
	public static void main(String[] args){    
        ConcretePropertype cp = new ConcretePropertype();    
        for(int i=0; i< 10; i++){    
            ConcretePropertype clonecp = (ConcretePropertype)cp.clone();    
            clonecp.show();    
        }    
    } 
}
