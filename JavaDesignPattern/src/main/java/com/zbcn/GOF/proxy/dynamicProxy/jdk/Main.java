package com.zbcn.GOF.proxy.dynamicProxy.jdk;

import com.zbcn.GOF.proxy.dynamicProxy.jdk.concrete.Business;
import com.zbcn.GOF.proxy.dynamicProxy.jdk.framework.IBusiness;
import com.zbcn.GOF.proxy.dynamicProxy.jdk.framework.JDKProxyFactory;

public class Main {

    public static void main(String[] args) {
        IBusiness business = new Business();
        JDKProxyFactory jdkProxyFactory = new JDKProxyFactory(business);
        IBusiness proxy = (IBusiness)jdkProxyFactory.getProxyInstance();
        proxy.talk("今天做了单大生意。。。");
    }
}
