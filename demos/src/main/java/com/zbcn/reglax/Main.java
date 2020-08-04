package com.zbcn.reglax;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则测试
 */
public class Main {

    public static void main(String[] args) {
//        test();
        test2("1");
        test2("0");
        test2("10");
        test2("19");
        test2("20");
        test2("29");
        test2("79");
        test2("99");
        test2("990");
        test2("900");
        test2("100");
        test2("199");
        test2("109");
        test2("1-9");
        test2("239");
        test2("245");
        test2("240");
        test2("244");
    }

    private static void test() {
        String reglex = "[\u4e00-\u9fa5a-zA-Z0-9]{0,16}";
        Pattern compile = Pattern.compile(reglex);
        Matcher aaa = compile.matcher("，则测试123123123");

        boolean matches = aaa.matches();
        System.out.println(matches);
    }

    private static void test2(String str){
        String regex1 = "^\\d+-\\d+";
        String regex = "^([1-9]|([1-9][0-9])|(1[0-9]{1,2})|(2[0-3][0-9])|(24[0-4]))$";
        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(str);
        System.out.println(matcher.matches());
    }
}
