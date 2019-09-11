package com.zbcn.pattern.jzz;

import com.zbcn.pattern.jzz.cppojo.Man;
import com.zbcn.pattern.jzz.cppojo.Person;

/**        
 * Title: ManBuilder.java
 * <p>    
 * Description: 角色ConcreteBuilder
 * @author likun       
 * @created 2018-3-15 下午12:57:54
 * @version V1.0
 */ 
public class ManBuilder implements PersonBuilder {
	
	private Person person;
	
	public ManBuilder() {  
        person = new Man();
   }

	public void builderHead() {
		person.setHead("建造男人的头");
	}

	public void buildBody() {
		person.setBody("建造男人的身体");

	}

	public void buildFoot() {
		person.setFoot("建造男人的脚");

	}

	public Person buildPerson() {
		 return person; 
	}

}
