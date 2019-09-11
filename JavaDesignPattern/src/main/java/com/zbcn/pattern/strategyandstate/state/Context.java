package com.zbcn.pattern.strategyandstate.state;

/**        
 * Title: Context.java
 * <p>    
 * Description: State manager 状态管理类
 * @author likun       
 * @created 2018-3-16 下午3:51:26
 * @version V1.0
 */ 
public class Context {
	/**我们将原来的 Color state 改成了新建的State state;  */
	private State state = null;
	
	/**     
	 * Description: setState是用来改变state的状态 使用setState实现状态的切换 
	 * <p>   
	 * @param state     
	 * @author likun      
	 * @created 2018-3-16 下午4:50:01      
	 */
	public void setState(State state){
		this.state = state;
	}
	
	public void push(){
		//状态的切换的细节部分,在本例中是颜色的变化,已经封装在子类的handlepush中实现,这里无需关心
		this.state.handlepush(this);
		//假设sample要使用state中的一个切换结果,使用getColor()
		System.out.println(state.getcolor());
	}
	public void pull(){
		this.state.handlepush(this);
	       //假设sample要使用state中的一个切换结果,使用getColor()  
		System.out.println(state.getcolor());
	}
	
}
