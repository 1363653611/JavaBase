package com.zbcn.pattern.visit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**        
 * Title: ObjectCtruture.java8
 * <p>    
 * Description: 构造器
 * @author likun       
 * @created 2018-3-16 下午2:07:18
 * @version V1.0
 */ 
public class ObjectCtruture {

	public static List<Element> getList() {
		List<Element> list = new ArrayList<Element>();
		Random ran = new Random();
		for (int i = 0; i < 10; i++) {
			int a = ran.nextInt(100);
			if (a > 50) {
				list.add(new ConcreteElement1());
			} else {
				list.add(new ConcreteElement2());
			}
		}
		return list;
	}
}
