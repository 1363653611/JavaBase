package com.zbcn.string;

/**
 *  String 字符串创建相关说明
 *  <br/>
 *  @author zbcn8
 *  @since  2020/7/31 10:44
 */
public class StringCreator {

    public static void main(String[] args) {
        test3();
    }

    private static void test1(){

        //先检查字符串常量池中有没有"abcd"，如果字符串常量池中没有，则创建一个，
        // 然后 str1 指向字符串常量池中的对象，如果有，则直接将 str1 指向"abcd""；
        String str1 = "abcd";

        String str2 = new String("abcd");//堆中创建一个新的对象
        String str3 = new String("abcd");//堆中创建一个新的对象
        System.out.println(str2==str3);//false
        System.out.println(str2==str3);//false
    }

    private static void test2(){
        String s1 = new String("计算机");
        String s2 = s1.intern();
        String s3 = "计算机";
        System.out.println(s2);//计算机
        System.out.println(s1 == s2);//false，因为一个是堆内存中的 String 对象一个是常量池中的 String 对象，
        System.out.println(s3 == s2);//true，因为两个都是常量池中的 String 对象
    }

    //字符串拼接
    private static void test3(){
        String str1 = "str";
        String str2 = "ing";

        String str3 = "str" + "ing";//常量池中的对象
        String str4 = str1 + str2; //在堆上创建的新的对象
        String str5 = "string";//常量池中的对象
        System.out.println(str3 == str4);//false
        System.out.println(str3 == str5);//true
        System.out.println(str4 == str5);//false
    }

    public static void test4(){
        String s1 = new String("abc");// 堆内存的地址值
        String s2 = "abc";
        System.out.println(s1 == s2);// 输出 false,因为一个是堆内存，一个是常量池的内存，故两者是不同的。
        System.out.println(s1.equals(s2));// 输出 true
    }
}
