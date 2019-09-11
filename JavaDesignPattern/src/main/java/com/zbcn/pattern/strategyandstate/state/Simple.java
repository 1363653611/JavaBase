package com.zbcn.pattern.strategyandstate.state;


/**        
 * Title: Simple.java
 * <p>    
 * Description: 测试方法
 * @author likun       
 * @created 2018-3-16 下午4:36:40
 * @version V1.0
 */ 
public class Simple {
	
	public static void main(String[] args){
		State red = new RedState(); 
		Context context = new Context();
		context.setState(red);
		context.pull();
	}

}
