package com.zbcn.reglax;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则测试
 */
public class Main {

    public static void main(String[] args) {
        String reglex = "[\u4e00-\u9fa5a-zA-Z0-9]{0,16}";
        Pattern compile = Pattern.compile(reglex);
        Matcher aaa = compile.matcher("，则测试123123123");

        boolean matches = aaa.matches();
        System.out.println(matches);

    }
}
