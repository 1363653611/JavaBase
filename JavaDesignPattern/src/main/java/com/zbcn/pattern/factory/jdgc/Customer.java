package com.zbcn.pattern.factory.jdgc;

import com.zbcn.pattern.factory.cppojo.BMW;

/**        
 * Title: Customer.java8
 * <p>    
 * Description: 客户端
 * @author likun       
 * @created 2018-3-15 上午10:51:45
 * @version V1.0
 */ 
public class Customer {
	public static void main(String[] args) {  
        Factory factory = new Factory();  
        BMW bmw320 = factory.createBMW(320);
        BMW bmw523 = factory.createBMW(523);  
    }  
}
