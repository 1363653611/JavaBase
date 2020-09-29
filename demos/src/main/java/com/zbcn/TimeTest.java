package com.zbcn;

import java.time.YearMonth;

public class TimeTest {

    public static void main(String[] args) {
        String s = "2019-02";
        YearMonth parse = YearMonth.parse(s);
        String s1 = parse.plusMonths(1L).toString();
        System.out.println(s1);
    }
}
