package com.zbcn.GOF.proxy.dynamicProxy.cglib;

import com.zbcn.GOF.proxy.dynamicProxy.cglib.concrete.UserDao;
import com.zbcn.GOF.proxy.dynamicProxy.cglib.framework.CGLIBProxyFactory;

public class Main {

    public static void main(String[] args) {
        //目标对象
        UserDao target = new UserDao();
        UserDao proxy = (UserDao)new CGLIBProxyFactory(target).getProxyInstance();
        proxy.save();
    }
}
