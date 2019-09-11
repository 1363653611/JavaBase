package com.zbcn.pattern.visit;

/**        
 * Title: ConcreteElement1.java8
 * <p>    
 * Description: 具体元素类1
 * @author likun       
 * @created 2018-3-16 下午1:55:33
 * @version V1.0
 */ 
public class ConcreteElement1 extends Element {

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
		
	}

	@Override
	public void doSomething() {
		 System.out.println("这是元素1"); 
		
	}

}
