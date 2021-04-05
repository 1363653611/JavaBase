package com.zbcn.common.base.baseType;

/**
 * 常见面试题
 */
public class T6_Exam {
    public static void main(String[] args) {
//        typeConvert();
//        errorConvert();
        //numConvert();
//        charTest();
        floatPrecision();
    }

    /*输出：false 0.4
    解析：有些浮点数不能准确的表示出来，与整数相乘之后出精度丢失，常见为小数位含3的
    */
    private static void floatPrecision() {
        System.out.println(0.1*3);
        System.out.println(0.1*3==0.3);
        System.out.println(0.1*4);
    }

    /**
     * char类型采用的是Unicode编码，Unicode编码包含汉字，所以char类型自然是可以存储一个汉字的
     */
    private static void charTest() {
        char c = '张';
        System.out.println(c);
    }


    /*输出：true true false true
    解析：自动装箱时采用valueOf方法，由于127在静态数组的范围内，所以不是new的，
    而128的两个引用是指向new出现对象的，所以第一个是true，第三个是false。
    而包装类和基本数据类型比较时，只要数值是相等的，就相等*/
    private static void numConvert() {
        Integer a1 = 127;
        Integer a2 = 127;
        int a3 = 127;
        Integer b1 = 128;
        Integer b2 = 128;
        int b3 = 128;
        System.out.println(a1 == a2);
        System.out.println(a1 == a3);
        System.out.println(b1 == b2);
        System.out.println(b1 == b3);
    }

    /*第几行的代码会出错？
    答案：第二行会出错，由于a+1变为了int类型,而int类型不能直接赋值给short类型
    但是+=这种情况是特殊的，所以不会出错；
    */
    private static void errorConvert() {
        short a = 1;    //第一行
        //a=a+1;		//第二行
        a += 1;        //第三行
    }

    /*输出：10.0   10
    解析：这里是一个很容易让人不注意的类型转化，这里a与b参与了运算，
    所以类型向类型大的方向转化，10就变成了10.0，但是a本身是没有变化的*/
    private static void typeConvert() {
        int a = 10;
        double b = 3.4;
        System.out.println(a > b ? a : b);
        System.out.println(a);
    }
}
