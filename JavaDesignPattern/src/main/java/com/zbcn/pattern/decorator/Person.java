package com.zbcn.pattern.decorator;

/**        
 * Title: Person.java8
 * <p>    
 * Description: 定义被装饰者，被装饰者初始状态有些自己的装饰
 * @author likun       
 * @created 2018-3-16 下午2:51:04
 * @version V1.0
 */ 
public class Person implements Human {

	@Override
	public void wearClothes() {
		System.out.println("穿什么呢。。");
	}

	@Override
	public void walkToWhere() {
		System.out.println("去哪里呢。。");
	}

}
