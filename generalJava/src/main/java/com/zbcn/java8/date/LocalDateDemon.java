package com.zbcn.java8.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.TemporalAdjusters.*;

/**
 * localDate 使用示例:LocalDate类型包含了年月日信息
 */
public class LocalDateDemon {


	public static void test(){
		//// 2018-04-20
		LocalDate of = LocalDate.of(2018, 4, 20);
		int year = of.getYear(); // 2018
		int value = of.getMonth().getValue();// 4
		int dayOfMonth = of.getDayOfMonth();//
		// 查看该月有多少天
		int days = of.lengthOfMonth(); // 30
		// 是否是闰年
		boolean isLeap = of.isLeapYear(); // false

		// 查看当天 年月日
		LocalDate today = LocalDate.now();
	}

	//ChronoField枚举类型包含了诸多的属性可供选择：
	private static void test2(){
		LocalDate date = LocalDate.now();

		int year1 = date.get(ChronoField.YEAR); // 2020
		System.out.println(year1);
		int month1 = date.get(ChronoField.MONTH_OF_YEAR); // 3
		System.out.println(month1);
		int day1 = date.get(ChronoField.DAY_OF_MONTH); // 1
		System.out.println(day1);
		// 当前日期属于该月第几周
		int weekOfMonth = date.get(ChronoField.ALIGNED_WEEK_OF_MONTH); // 1
		System.out.println(weekOfMonth);
	}

	//我们可以按照需求修改时间
	private static void modifyTest(){
		LocalDate date3 = LocalDate.of(2018, 4, 20); // 2018-04-20
		LocalDate date4 = date3.withDayOfMonth(22); // 2018-04-22
		LocalDate date5 = date3.with(ChronoField.DAY_OF_MONTH, 22); // 2018-04-22
		LocalDate date6 = date3.withYear(2019); // 2019-04-20
		LocalDate date7 = date3.plusDays(5); // 2018-04-25
		LocalDate date8 = date3.plus(5, ChronoUnit.DAYS); // 2018-04-25
		LocalDate date9 = date3.minusYears(10); // 2008-04-20
	}


	//temporalAdjusters类提供了许多静态方法来修改LocalDate对象。当我们需要获取下一个周天，下一个工作日，本月的最后一天等信息时，TemporalAdjusters类便可派上用场：
	 private static void modify2(){
		 LocalDate date3 = LocalDate.now();
		 LocalDate date10 = date3.with(nextOrSame(DayOfWeek.MONDAY)); // 2018-04-23
		 LocalDate date11 = date3.with(lastDayOfMonth()); // 2018-04-30
		 LocalDate date12 = date3.with(previous(DayOfWeek.SATURDAY)); // 2018-04-14
	 }

	 //格式化
	private static void format(){
		LocalDate date = LocalDate.now();
		String str1 = date.format(DateTimeFormatter.BASIC_ISO_DATE); // 20180420
		String str2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE); // 2018-04-20

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String str5 = date.format(dtf); // 2018-04-20
		LocalDate date13 = LocalDate.parse(str5, dtf); // 2018-04-20
	 }

	public static void main(String[] args) {
		test2();
	}
}
