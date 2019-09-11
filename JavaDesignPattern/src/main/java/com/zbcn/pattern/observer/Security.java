package com.zbcn.pattern.observer;

/**        
 * Title: Security.java
 * <p>    
 * Description: 具体的观察者：保镖
 * @author likun       
 * @created 2018-3-15 下午6:17:00
 * @version V1.0
 */ 
public class Security implements Watcher {

	public void update() {
		System.out.println("运输车有行动，保安贴身保护");
	}

}
