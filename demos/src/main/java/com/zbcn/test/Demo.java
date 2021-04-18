package com.zbcn.test;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Demo {

    //输入：“my_name____is__dd”
    //输出：“dd_is____name__my”
    //字符串由单词和下划线组成，要求单词倒序输出、下划线正序输出
    public static void main(String[] args) {
        String str = "my_name____is__dd";
        //String s = soutNewStr(str);
        String s = soutNewStr2(str);
        System.out.println(s);

    }

    private static String soutNewStr2(String str) {
        //切割字符串
        StringBuilder target = new StringBuilder();
        StringBuilder word = new StringBuilder();
        List<String> list = new ArrayList();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '_') {
                if (StringUtils.isNotBlank(word.toString())) {
                    list.add(word.toString());
                    word = new StringBuilder();
                }
                target.append('_');
            } else {
                if (StringUtils.isNotBlank(target.toString())) {
                    list.add(target.toString());
                    target = new StringBuilder();
                }
                word.append(c);
            }
        }
        if (StringUtils.isNotBlank(word.toString())) {
            list.add(word.toString());
        }
        if (StringUtils.isNotBlank(target.toString())) {
            list.add(target.toString());
        }
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (s.contains("_")) {
                continue;
            }
            for (int j = list.size()-1; j >= 0; j--) {
                String t = list.get(j);
                if (s.contains("_")) {
                    continue;
                }
                list.add(j, s);
                list.add(i, t);
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            result.append(list.get(i));
        }
        return result.toString();
    }

    private static String soutNewStr(String str) {
        Stack<String> stack = new Stack<>();
        StringBuilder target = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '_') {
                if (StringUtils.isNotBlank(target.toString())) {
                    stack.push(target.toString());
                    target = new StringBuilder();
                }
                continue;
            } else {
                target.append(c);
            }
        }
        if (StringUtils.isNotBlank(target.toString())) {
            stack.push(target.toString());
        }

        StringBuilder dest = new StringBuilder();
        boolean isNotStr = false;
        int i = 0;
        while (i < str.length()) {
            char c = str.charAt(i);
            if (c == '_' && !isNotStr) {
                isNotStr = true;
                String pop = stack.pop();
                if (StringUtils.isNotBlank(dest.toString())) {
                    String s = dest.toString();
                    str = str.replace(s, pop);
                    dest = new StringBuilder();
                    i += (pop.length() - s.length());
                }
            } else if (c == '_' && isNotStr) {

            } else {
                isNotStr = false;
                dest.append(c);
            }
            i++;
        }
        return str;
    }
}
