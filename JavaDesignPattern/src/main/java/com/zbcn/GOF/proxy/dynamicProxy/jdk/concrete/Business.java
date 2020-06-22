package com.zbcn.GOF.proxy.dynamicProxy.jdk.concrete;

import com.zbcn.GOF.proxy.dynamicProxy.jdk.framework.IBusiness;

/**
 * 具体的实现
 */
public class Business  implements IBusiness {
    @Override
    public void talk(String something) {
        System.out.println("张三:" + something);
    }
}
