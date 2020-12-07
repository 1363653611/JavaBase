package com.zbcn.common.base.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ReflectDemo {

    public static void main(String[] args) {
        //test1();

        Field[] declaredFields = Person.class.getDeclaredFields();
        for (Field field : declaredFields){
            String name = field.getName();
            boolean synthetic = field.isSynthetic();
            System.out.println(name + "值");
            System.out.println(synthetic);
            int modifiers = field.getModifiers();
            boolean isStatic = Modifier.isStatic(modifiers);
            System.out.println("是否为静态属性：" + isStatic);

        }
    }

    private static void test1() {
        Class<ReflectDemo> reflectDemoClass = ReflectDemo.class;
        System.out.println(reflectDemoClass.getName());

        ReflectDemo reflectDemo = new ReflectDemo();
        System.out.println(reflectDemo.getClass().getName());
        Class<?> aClass = null;
        try {
            aClass = Class.forName("com.zbcn.demo.base.reflect.ReflectDemo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(aClass.getName());
    }
}
