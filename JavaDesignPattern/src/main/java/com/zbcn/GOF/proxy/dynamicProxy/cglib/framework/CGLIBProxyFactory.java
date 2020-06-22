package com.zbcn.GOF.proxy.dynamicProxy.cglib.framework;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib 动态代理工厂
 *
 * Cglib代理,也叫作子类代理,它是在内存中构建一个子类对象从而实现对目标对象功能的扩展.
 * Cglib包的底层是通过使用字节码处理框架ASM来转换字节码并生成新的子类.
 * 代理的类不能为final,否则报错
 * <br/>
 *
 * @author zbcn8
 * @since 2020/6/22 19:31
 */
public class CGLIBProxyFactory  implements MethodInterceptor {

    //维护目标对象
    private Object target;

    public CGLIBProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance(){
        //1.工具类
        Enhancer en = new Enhancer();
        //2.设置父类
        en.setSuperclass(target.getClass());
        //3.设置回调函数
        en.setCallback(this);
        //4.创建子类(代理对象)
        return en.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始事务...");
        Object invoke = method.invoke(target, objects);
        System.out.println("提交事务...");
        return invoke;
    }
}
