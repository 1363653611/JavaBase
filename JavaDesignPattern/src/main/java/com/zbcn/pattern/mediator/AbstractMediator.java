package com.zbcn.pattern.mediator;

/**        
 * Title: AbstractMediator.java
 * <p>    
 * Description: 抽象的中介者
 * @author likun       
 * @created 2018-3-23 下午2:38:35
 * @version V1.0
 */ 
abstract class AbstractMediator {
	
	protected AbstractColleague A;
	protected AbstractColleague B;
	public AbstractMediator(AbstractColleague a, AbstractColleague b) {
		super();
		A = a;
		B = b;
	}
	
	/**     
	 * Description: 处理A影响B的逻辑
	 * <p>        
	 * @author likun      
	 * @created 2018-3-23 下午2:42:37      
	 */
	public abstract void AaffectB();
	
	/**     
	 * Description: 处理B影响A的逻辑
	 * <p>        
	 * @author likun      
	 * @created 2018-3-23 下午2:42:55      
	 */
	public abstract void BaffectA();
	
}
