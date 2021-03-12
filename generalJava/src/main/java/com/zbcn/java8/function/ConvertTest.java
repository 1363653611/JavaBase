package com.zbcn.java8.function;

public class ConvertTest {

    public static void main(String[] args) {
        //定义一个将 String 转换为 int 的具体方法实现
        Converter<String, Integer> intConvert= (from) -> Integer.parseInt(from);
        //Converter<String, Integer> intConvert= Integer::parseInt;
        Integer convert = intConvert.convert("123");
        System.out.println(convert);
    }
}
