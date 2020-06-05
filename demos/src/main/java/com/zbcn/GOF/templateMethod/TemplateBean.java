package com.zbcn.GOF.templateMethod;

/**
 * 模板对象,定义模板方法和算法
 */
public abstract class TemplateBean {

    /**
     * 模板方法,定义算法
     */
    public void  templateMethod(){
        //先调用方法2
        method1();
        for (int i=1; i <5; i++){
            //调用 四遍方法2
            method2();
        }
        method3();
    }

    /**
     * 抽象方法一
     */
    public abstract void method1();

    /**
     * 抽象方法2
     */
    public  abstract  void method2();

    /**
     * 抽象方法3
     */
    public abstract  void method3();
}
