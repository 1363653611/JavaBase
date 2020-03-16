package com.zbcn.java8.date;

import java.time.LocalDate;
import java.time.Period;

public class PeriodDemon {

	public static void test(){
		LocalDate date1 = LocalDate.now();
		LocalDate date2 = LocalDate.of(2018, 5, 21);
		Period period = Period.between(date1, date2);
		int monthsBetween = period.getMonths(); // 1
		int daysBetween = period.getDays(); // 1
	}

	private static void create(){
		Period tenDays = Period.ofDays(10);
		Period threeWeeks = Period.ofWeeks(3);
		Period twoYearsSixMonthsOneDay = Period.of(2, 6, 1);

	}
}
