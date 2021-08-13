package com.zbcn.test;

public class StringTest {

    public static void main(String[] args) {
        test6();
    }

    public static void test5(){
        String s = new String("1"); // 堆空间
        String s3 = s.intern(); //  常量池
        String s2 = "1";//  常量池

        System.out.println(s == s2); // false
        System.out.println(s == s3); // false
        System.out.println(s3 == s2); // true

        String s4 = new String("1") + new String("1");
        String s41 = new String("11");
        String s5 = s4.intern();
//        String s6 = "1" + "1";
        String s7 = "11";
//        System.out.println(s4 == s6);
//        System.out.println(s5 == s6);
//        System.out.println(s5 == s7);
//        System.out.println(s6 == s7);
        System.out.println(s4 == s7);
        System.out.println(s4 == s5);
        //System.out.println(s7 == s5);
    }

    public static void  test6(){
        String s1 = new String("1");
        String s2 = "1";
        System.out.println("s1==s2:" + (s1 == s2));
        s1 = s1.intern();
        System.out.println("s1==s2:" + (s1 == s2));
        String s3 = new String("1") + new String("1");
        s3.intern();
        String s31 = "1" + "1";
        String s32 = "11";
        System.out.println("s3==s31:" + (s3 == s31));
        System.out.println("s31==s32:" + (s31 == s32));
        String s4 = "11";

        System.out.println("s3==s4:" + (s3 == s4));

//        s3 = s3.intern();
//        System.out.println("s3==s4:" + (s3 == s4));
    }
}
