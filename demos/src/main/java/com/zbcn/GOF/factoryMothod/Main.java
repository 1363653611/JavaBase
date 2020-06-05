package com.zbcn.GOF.factoryMothod;

import com.zbcn.GOF.factoryMothod.concrete.CarFactory;
import com.zbcn.GOF.factoryMothod.framework.Product;

/**
 *  @title Main
 *  @Description 工厂方法测试客户端
 *  @author zbcn8
 *  @Date 2020/6/5 15:46
 */
public class Main {


    public static void main(String[] args) {
        CarFactory carFactory = new CarFactory();
        Product zs = carFactory.create("张三");
        Product ls = carFactory.create("李四");
        zs.use();
        ls.use();
    }
}
