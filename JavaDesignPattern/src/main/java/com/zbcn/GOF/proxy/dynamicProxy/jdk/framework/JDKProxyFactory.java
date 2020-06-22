package com.zbcn.GOF.proxy.dynamicProxy.jdk.framework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zbcn8
 * @title JDKProxyFactory
 * @description jdk 动态代理工厂
 * @since 2020/6/22 18:58
 */
public class JDKProxyFactory {

    //需要代理的目标对象
    private static Object target;

    public JDKProxyFactory(Object target){
        this.target = target;
    }

    public static JDKProxyFactory builder(Object t){
        return new JDKProxyFactory(t);
    }

    public Object getProxyInstance(){
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),//类加载器
                target.getClass().getInterfaces(), // 类实现的接口
                new InvocationHandler(){ // 调用处理器,执行目标对象的方法时,会触发调用处理器的方法
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("张三高兴的");
                        Object invoke = method.invoke(target, args);
                        System.out.println("说完更高兴了");
                        return invoke;
                    }
                });

    }
}
