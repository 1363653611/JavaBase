package com.zbcn.pattern.visit;

/**        
 * Title: ConcreteElement2.java
 * <p>    
 * Description: 具体元素类2
 * @author likun       
 * @created 2018-3-16 下午1:55:13
 * @version V1.0
 */ 
public class ConcreteElement2 extends Element{

	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
		
	}

	@Override
	public void doSomething() {
		System.out.println("这是元素2");
		
	}

}
