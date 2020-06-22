package com.zbcn.GOF.proxy.dynamicProxy.cglib.concrete;

/**
 *  @title UserDao
 *  @description 需要被代理的对象
 *
 *  @author zbcn8
 *  @since  2020/6/22 19:28
 */
public class UserDao {

    public void save() {
        System.out.println("----已经保存数据!----");
    }
}
