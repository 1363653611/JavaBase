package com.zbcn.GOF.templateMethod;

/**
 *  @title ConcreteBean
 *  @Description 具体的bean,继承抽象bean
 *  @author zbcn8
 *  @Date 2020/6/5 16:27
 */
public class ConcreteBean extends TemplateBean {
    @Override
    public void method1() {
        System.out.println("抽象方法1执行");
    }

    @Override
    public void method2() {
        System.out.println("抽象方法2执行");
    }

    @Override
    public void method3() {
        System.out.println("抽象方法3执行");
    }
}
