package com.zbcn.concurrency.context;

/**
 * @ClassName: Test
 * @Description: 测试值传递和引用传递
 * @author Administrator
 * @date 2019-08-08 11:16
 *
 */
public class Test {

	public static void main(String[] args) {
		//testBasicType();
		
		testType();
	}
	
	private static void testType() {
		Person p = new Person("张三",1);
		System.out.println("付值前：" + p);
		changeP(p);
		Person pp = new Person("李四",2);
		Person[] array = new Person[5];
		array[0] = p;
		System.out.println("数组中的：" + array[0]);
		
		p = pp;
		System.out.println("付值后：" + p);
	}
	
	public static void changeP(Person p) {
		p = new Person("王五", 3);
		System.out.println("changeP" + p);
	}

	private static void testBasicType() {
		int a = 2;
		int b = 1;
		int [] array = new int[5];
		array[0] = a;
		a = 1;
		//
		System.out.println("数组" + array[0]);
		System.out.println("原来值：" + a);
	}
	
	static class Person {
		private String name;
		
		private int age;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public Person(String name, int age) {
			super();
			this.name = name;
			this.age = age;
		}
		
		
	}
	
	
}


