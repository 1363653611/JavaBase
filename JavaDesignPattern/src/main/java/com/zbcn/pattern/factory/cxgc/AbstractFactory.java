package com.zbcn.pattern.factory.cxgc;

/**        
 * Title: AbstractFactory.java
 * <p>    
 * Description: 创建工厂的接口  
 * @author likun       
 * @created 2018-3-15 上午11:08:56
 * @version V1.0
 */ 
public interface AbstractFactory {
	//制造发动机  
    public Engine createEngine();  
    //制造空调   
    public Aircondition createAircondition();
}
