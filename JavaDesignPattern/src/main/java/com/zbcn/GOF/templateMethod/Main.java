package com.zbcn.GOF.templateMethod;

/**
 * 模板方法客户端
 */
public class Main {

    public static void main(String[] args) {
        ConcreteBean concreteBean = new ConcreteBean();
        concreteBean.templateMethod();
    }
}
