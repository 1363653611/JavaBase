package com.zbcn.pattern.visit;

/**        
 * Title: Element.java
 * <p>    
 * Description: 抽象元素
 * @author likun       
 * @created 2018-3-16 下午1:50:08
 * @version V1.0
 */ 
public abstract class Element {
	public abstract void accept(IVisitor visitor);
	
	public abstract void doSomething();
}
