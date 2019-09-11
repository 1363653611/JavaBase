package com.zbcn.pattern.jzz;

import com.zbcn.pattern.jzz.cppojo.Person;

/**        
 * Title: Test.java8
 * <p>    
 * Description: 测试建造这模式
 * @author likun       
 * @created 2018-3-15 下午1:01:18
 * @version V1.0
 */ 
public class Test {

	public static void main(String[] args) {
		PersonDirector pd = new PersonDirector();
		Person manPerson  = pd.constructPerson(new ManBuilder());
		Person WomanPerson = pd.constructPerson(new WomanBuilder());
		
	}
}
