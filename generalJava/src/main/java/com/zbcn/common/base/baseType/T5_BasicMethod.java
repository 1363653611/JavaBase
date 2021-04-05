package com.zbcn.common.base.baseType;

public class T5_BasicMethod {

    public static void main(String[] args) {
//        valueOf();
//        paseXXX();
//        xxxValue();
        autoPackage();


    }

    private static void autoPackage() {
        /*自动装箱：valueOf*/
        Integer i=123;//原理是 Integer i=Integer.valueOf(123);

        /*自动拆箱*/
        int i1=i+1;//原理是	int i1=i.intValue()+1;

        /*原理为Integer c=Integer.valueOf(a.intValue()+b.intValue());*/
        Integer a=123;
        Integer b=123;
        Integer c=a+b;
    }

    private static void xxxValue() {
        /*作用：将包装类对象转换为对应的基本数据类型*/

        Integer a=Integer.valueOf(100);//将基本数据类型转换为包装类对象
        int b=a.intValue();//将包装类对象转换为对应的基本数据类型
        System.out.println(b);//输出100

        Double c=Double.valueOf(2.33);
        double d=c.doubleValue();
        System.out.println(d);
    }

    private static void paseXXX() {
        /*作用：将给定字符串装换为对应的基本数据类型
         * 前提是该字符串必须正确描述该基本数据类型表示的值*/
        int a=Integer.parseInt("100");
        System.out.println(a);//输出100

        int b=Integer.parseInt("100a");
        System.out.println(b);//运行错误，字符串的值不为int类型
    }

    private static void valueOf() {
        /*1.参数为基本数据类型
         * 作用：将基本数据类型转换为对应包装类 * */
        Integer i=Integer.valueOf(10);
        System.out.println(i);//输出10

        /*2.参数为String字符串时，
         * 作用：返回指定字符串值的包装类对象
         * 	*/
        Integer a=Integer.valueOf("100");
        System.out.println(a);//输出100

        Integer b=Integer.valueOf("100a");
        System.out.println(b);//运行错误，字符串的值不少一个int类型的
    }
}
