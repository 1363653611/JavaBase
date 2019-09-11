package com.zbcn.pattern.strategyandstate.strategy;

/**        
 * Title: Strategy.java8
 * <p>    
 * Description: 策略模式的管理类
 * @author likun       
 * @created 2018-3-16 下午6:47:30
 * @version V1.0
 */ 
public class Strategy {
	IStrategy iStrategy;
	public Strategy(IStrategy strategy){
		iStrategy = strategy;
	}
	
	public double countMoney(double money){
		return iStrategy.countMoney(money);
	}
}
