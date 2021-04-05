package com.zbcn.common.base.baseType;

/**
 * 计算过程中的数据转换
 */
public class T4_Calculate {

    //运算时，运算结果会向较大的类型转换
    public static void main(String[] args) {
//        a();
//        b();
        //c();

        d();

    }

    private static void d() {
        int a=10000000;
        int b=10000000;
        int c=a*b;
        System.out.println(c);//输出276447232，得到的结果超出了int类型的范围，数据溢出
    }

    private static void c() {
        int a=128;
        byte b=(byte)a;
        System.out.println(b);//输出-128，出现了数据溢出

        double c=1.23;
        int d=(int)c;
        System.out.println(d);//输出1，精度丢失
    }

    private static void b() {
        byte a=3;
        byte b=4;
        /*byte c=a+b;
         * 编译错误,此处由于byte类型参与运算时，先直接转换为int类型，
         * 所以最后的结果也是int类型，但是得出的结果不能叫做int类型的直接量，所以编译错误
         * */
        int d=a+b;
    }

    private static void a() {
        int a=3;
        double b=4;
        System.out.println(a+b);//输出7.0

        float c=3.2f;
        //c=c+3.14;
        /* 编译错误,运算之后变为double类型*/
    }
}
