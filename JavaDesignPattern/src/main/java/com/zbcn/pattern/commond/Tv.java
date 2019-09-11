package com.zbcn.pattern.commond;

/**        
 * Title: Tv.java8
 * <p>    
 * Description: 命令的接收者Reciver（实际操作命令的人）
 * @author likun       
 * @created 2018-3-23 下午4:39:23
 * @version V1.0
 */ 
public class Tv {

	public int currentChanller = 0;
	
	public void turnOn(){
		System.out.println("The television is on");
	}
	
	public void turnOff(){
		System.out.println("The television is off");
	}
	
	public void changeChannel(int channel){
		this.currentChanller = channel;
		System.out.println("Now TV channel is" + channel);
	}
}
