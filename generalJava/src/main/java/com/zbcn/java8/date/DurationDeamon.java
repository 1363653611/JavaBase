package com.zbcn.java8.date;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 *  @title DurationDeamon
 *  @Description Duration用于计算两个LocalTime或者LocalDateTime的时间差
 *  @author zbcn8
 *  @Date 2020/3/1 11:44
 */
public class DurationDeamon {


	private static void test(){
		LocalTime time1 = LocalTime.now();
		LocalTime time2 = LocalTime.of(23, 59, 59);
		//time1和time2之间相差的毫秒
		Duration duration = Duration.between(time1, time2);
		long seconds = duration.getSeconds(); // 13565
	}

	private static void create(){
		// 创建了一个3分钟的Duration，两种创建方式等价
		Duration threeMinutes = Duration.ofMinutes(3);
		threeMinutes = Duration.of(3, ChronoUnit.MINUTES);


	}
}
