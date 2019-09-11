package com.zbcn.pattern.mediator;

public abstract class AbstractColleague {
	protected int number;
	
	public int getNumber(){
		return this.number;
	}
	
	public void setNumber(int number){
		this.number = number;
	}
	
	/**     
	 * Description: 这里的参数是一个中介者
	 * <p>   
	 * @param number 数值
	 * @param am     中介者
	 * @author likun      
	 * @created 2018-3-23 下午2:36:08      
	 */
	public abstract void setNumber(int number,AbstractMediator am);
}
