package com.zbcn.pattern.facade;

/**
 * 门面角色类
 *
 * @author
 * @create 2018-05-24 20:27
 **/
public class Facade {

    public void test(){
        ModuleA a = new ModuleA();
        a.testA();
        ModuleB b = new ModuleB();
        b.testB();
        ModuleC c = new ModuleC();
        c.testC();
    }

    /**
     * 测试方法
     * @param args
     */
    public static void main(String[] args) {

        Facade facade = new Facade();
        facade.test();
    }
}
