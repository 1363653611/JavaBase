package com.zbcn.GOF.prototype.framework;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 使用 product 接口来复制实例
 */
public class Manager {
    /**
     * 实例的名字和实例之间的关系
     */
    private Map<String,Product> showCase = Maps.newHashMap();

    /**
     * 注册实例的方法
     * @param name
     * @param product
     */
    public void register(String name, Product product){
        showCase.put(name,product);
    }

    /**
     * 具体创建clone对象的方法
     * @param name
     * @return
     */
    public Product create(String name){
        Product product = showCase.get(name);
        return product.createClone();
    }

}
