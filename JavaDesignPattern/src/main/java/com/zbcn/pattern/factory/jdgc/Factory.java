package com.zbcn.pattern.factory.jdgc;

import com.zbcn.pattern.factory.cppojo.BMW;
import com.zbcn.pattern.factory.cppojo.BMW320;
import com.zbcn.pattern.factory.cppojo.BMW523;

/**        
 * Title: Factory.java
 * <p>    
 * Description: 工厂方法，简单的工厂模式，导致工厂方法为全能类
 * @author likun       
 * @created 2018-3-15 上午10:52:06
 * @version V1.0
 */ 
public class Factory {
	
	public BMW createBMW(int type) {
        switch (type) {  
          
        case 320:  
            return new BMW320();
  
        case 523:  
            return new BMW523();
  
        default:  
            break;  
        }  
        return null;  
    }  
}
