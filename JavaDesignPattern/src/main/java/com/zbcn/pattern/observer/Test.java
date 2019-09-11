package com.zbcn.pattern.observer;

/**        
 * Title: Test.java8
 * <p>    
 * Description: 测试类
 * @author likun       
 * @created 2018-3-15 下午6:32:08
 * @version V1.0
 */ 
public class Test {
	
	public static void main(String[] args){
		Transporter transporter = new Transporter();
		Police police = new Police();  
        Security security = new Security();  
        Thief thief = new Thief(); 
        transporter.addWatcher(police);  
        transporter.addWatcher(security);  
        transporter.addWatcher(thief);  
        transporter.notifyWatchers(); 
	}

}
