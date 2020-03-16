package com.zbcn.java8.date;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.VoidType;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *  @title LocalTimeDemon
 *  @Description LocalTime和LocalDate类似，区别在于LocalTime包含的是时分秒（毫秒）信息
 *  @author zbcn8
 *  @Date 2020/3/1 11:39
 */
public class LocalTimeDemon {

	private static void testTime(){
		LocalTime time = LocalTime.of(20, 13, 54); // 20:13:54
		int hour = time.getHour(); // 20
		int minute = time.getMinute(); // 13
		int second = time.getSecond(); // 54
	}

	private static void test(){
		LocalDate date = LocalDate.parse("2018-04-20");
		LocalTime time = LocalTime.parse("20:13:54");
	}
}
