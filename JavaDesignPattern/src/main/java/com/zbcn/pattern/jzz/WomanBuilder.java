package com.zbcn.pattern.jzz;

import com.zbcn.pattern.jzz.cppojo.Person;
import com.zbcn.pattern.jzz.cppojo.Woman;

public class WomanBuilder implements PersonBuilder {
	
	private Person person;
	public WomanBuilder() {  
        person = new Woman();
   } 

	public void builderHead() {
		person.setHead("建造女人的头"); 
	}

	public void buildBody() {
		person.setBody("建造女人的身体");
	}

	public void buildFoot() {
		 person.setFoot("建造女人的脚");
	}

	public Person buildPerson() {
		return person;
	}

}
