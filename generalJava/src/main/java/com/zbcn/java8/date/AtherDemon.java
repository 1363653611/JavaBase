package com.zbcn.java8.date;

import java.time.LocalDate;
import java.time.MonthDay;

/**
 * 其他测试示例
 */
public class AtherDemon {

	private static void test(){
		LocalDate date = LocalDate.now();
		LocalDate date15 = LocalDate.of(2018,4,21);
		date.isEqual(date15); // false
		date.isAfter(date15); // false
		date.isBefore(date15); // true
	}

	//MonthDay只包含月日信息，可以用于存放类似于生日，结婚纪念日等信息。
	//假如用户的生日是1999年9月9号，那么可以通过这种方法来判断今天是否是用户的生日，如果是的话便发送生日祝福。同样的也有YearMonth类。
	private static void test2(){
		LocalDate birthday = LocalDate.of(1999, 9, 9);
		MonthDay monthDay = MonthDay.of(birthday.getMonth(), birthday.getDayOfMonth());
		MonthDay currentMonthDay = MonthDay.from(LocalDate.now());
		if (currentMonthDay.equals(monthDay)) {
			System.out.println("happy birthday!");
		}
	}

}
