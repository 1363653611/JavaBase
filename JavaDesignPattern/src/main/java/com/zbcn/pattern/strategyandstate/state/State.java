package com.zbcn.pattern.strategyandstate.state;

import java.awt.Color;

/**        
 * Title: State.java8
 * <p>    
 * Description: 状态的父类
 * @author likun       
 * @created 2018-3-16 下午3:44:03
 * @version V1.0
 */ 
public abstract class State {
	
	public abstract void handlepush(Context c);
	
	public abstract void handlepull(Context c);
	
	public abstract Color getcolor();
}
