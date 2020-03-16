package com.zbcn.java8.option;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 *  @title OptionalDemon
 *  @Description optional 功能测试
 *  @author zbcn8
 *  @Date 2020/3/1 22:38
 */
public class OptionalDemon {

	private static void test(){
		//创建一个空的Optional对象
		Optional<Object> empty = Optional.empty();
		//创建一个非空的optional 对象
		Girl lili = new Girl("lili");
		Employee employee = new Employee(lili);
		//我们也可以使用静态工厂方法Optional.of来创建一个非空对象的Optional对象：
		//如果employee为空，这段代码会立即抛出一个NullPointerException。
		Optional<Employee> optEmployee = Optional.of(employee);
		//创建可以为null的Optional
		Optional<Employee> nullAbleEmployee = Optional.ofNullable(employee);
		if(nullAbleEmployee.isPresent()){// 如果值存在返回true，否则返回false
			System.out.println(nullAbleEmployee.get().getGirlFriend());
		}
		//ifPresent
		//如果Optional实例有值则为其调用Consumer（函数描述符为T -> void），否则不做处理
		nullAbleEmployee.ifPresent( e -> System.out.println(e.getGirlFriend().getName()));

		//Optional实例有值则将其返回，否则返回orElse方法传入的参数
		System.out.println(Optional.empty().orElse("There is no value present!"));

		//orElseGet与orElse方法类似，orElse方法将传入的字符串作为默认值，而orElseGet方法可以接受Supplier（函数描述符为() -> T）来生成默认值
		Girl aa = Optional.of(lili).orElseGet(() -> new Girl("aa"));

		//orElseThrow
		//如果有值则将其返回，否则抛出Supplier接口创建的异常
		Optional.empty().orElseThrow(NoSuchElementException::new);

		//map
		//如果Optional有值，则对其执行调用Function函数描述符为（T -> R）得到返回值。如果返回值不为null，则创建包含Function回值的Optional作为map方法返回值，否则返回空Optional
		Girl girl = optEmployee.map(e -> e.getGirlFriend()).orElse(new Girl("lili"));

		//flatMap
		Girl lili1 = optEmployee.flatMap(e -> Optional.of(e.getGirlFriend()))//latMap与map方法类似，区别在于flatMap中的Function函数返回值必须是Optional
				.orElse(new Girl("lili"));

		//filter
		Optional<String> name = Optional.of("Jane");
		Optional<String> LongName = name.filter((value) -> value.length() >= 3);
		System.out.println(LongName.orElse("名字长度小于3个字符"));
	}

	//传统方法
	static String getGirlFriendName1(Department department) {
		if (department != null) {
			Employee employee = department.getEmployee();
			if (employee != null) {
				Girl girl = employee.getGirlFriend();
				if (girl != null) {
					return girl.getName();
				}
				return "单身汪";
			}
			return "没有员工";
		}
		return "部门为空";
	}

	// 使用optional
	private static String getGirlFriendName(Department department) {
		Optional<Department> opt = Optional.ofNullable(department);
		return opt.map(Department::getEmployee)
				.map(Employee::getGirlFriend)
				.map(Girl::getName)
				.orElseThrow(NoSuchElementException::new);
	}
}

class Department {
	private Employee employee;
	public Department(Employee employee) {
		this.employee = employee;
	}
	Employee getEmployee() {
		return employee;
	}
}

class Employee {
	private Girl girlFriend;

	public Employee(Girl girlFriend) {
		this.girlFriend = girlFriend;
	}

	Girl getGirlFriend() {
		return girlFriend;
	}
}

class Girl {
	private String name;

	public Girl(String name) {
		this.name = name;
	}

	String getName() {
		return name;
	}
}
