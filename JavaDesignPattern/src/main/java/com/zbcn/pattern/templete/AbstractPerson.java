package com.zbcn.pattern.templete;

public abstract class AbstractPerson {
	/**     
	 * Description: 准备去学校
	 * <p>        
	 * @author likun      
	 * @created 2018-3-16 上午9:56:02      
	 */
	public void prepareGotoSchool(){
		dressUp();
		eatBreakfast();
		takeThings();
	}
	//以下是不同子类根据自身特性完成的具体步骤  
	protected abstract void dressUp();  
    protected abstract void eatBreakfast();  
    protected abstract void takeThings();  
}
