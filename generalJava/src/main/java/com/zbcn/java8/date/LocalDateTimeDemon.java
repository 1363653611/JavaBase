package com.zbcn.java8.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *  @title LocalDateTimeDemon
 *  @Description LocalDateTime是LocalDate和LocalTime的组合形式，包含了年月日时分秒信息。
 *  @author zbcn8
 *  @Date 2020/3/1 11:41
 */
public class LocalDateTimeDemon {

	private static void test(){
		LocalDate date = LocalDate.parse("2018-04-20");
		LocalTime time = LocalTime.parse("20:13:54");
		LocalDateTime ldt1 = LocalDateTime.of(2018, 4, 20, 20, 13, 54); // 2018-04-20T20:13:54
		LocalDateTime ldt2 = LocalDateTime.of(date, time); // 2018-04-20T20:13:54
		//LocalDateTime可以转换为LocalDate和LocalTime，转换后包含的信息减少了：
		LocalDate date1 = ldt1.toLocalDate(); // 2018-04-20
		LocalTime time1 = ldt1.toLocalTime(); // 20:13:54
		//同样的，LocalDate和LocalTime也可以转换为LocalDateTime，只需要补上日期或者时间：
		LocalDateTime ldt3 = date.atTime(time); // 2018-04-20T20:13:54
		LocalDateTime ldt4 = date.atTime(20, 13, 54); // 2018-04-20T20:13:54
		LocalDateTime ldt5 = time.atDate(date); // 2018-04-20T20:13:54
	}
}
