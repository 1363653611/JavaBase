package com.zbcn.pattern.strategyandstate.simplestate;

/**        
 * Title: FileSub.java
 * <p>    
 * Description: 策略控制类.
 * @author likun       
 * @created 2018-3-20 下午4:41:47
 * @version V1.0
 */ 
public class FileSub {
	
	private IState istate;
	
	public FileSub(){
		istate = new BeginState();
	}

	public void setState(IState istate) {
		this.istate = istate;
		
	}
	
	public void submit(){
		istate.submit(this);
	}

}
