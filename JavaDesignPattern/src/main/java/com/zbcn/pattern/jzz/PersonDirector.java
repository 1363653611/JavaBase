package com.zbcn.pattern.jzz;

import com.zbcn.pattern.jzz.cppojo.Person;

/**        
 * Title: PersonDirector.java8
 * <p>    
 * Description: 构造一个使用Builder接口的对象，指导构建过程
 * @author likun       
 * @created 2018-3-15 下午1:03:11
 * @version V1.0
 */ 
public class PersonDirector {
	
	public Person constructPerson(PersonBuilder pb){
		pb.builderHead();  
        pb.buildBody();  
        pb.buildFoot();  
        return pb.buildPerson(); 
	}
}
