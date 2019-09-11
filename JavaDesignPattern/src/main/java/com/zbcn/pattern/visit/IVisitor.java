package com.zbcn.pattern.visit;

/**        
 * Title: IVisitor.java
 * <p>    
 * Description: 抽象访问者
 * @author likun       
 * @created 2018-3-16 下午1:51:58
 * @version V1.0
 */ 
public interface IVisitor {
	public void visit(ConcreteElement1 el1);
	
	public void visit(ConcreteElement2 el2);
}
