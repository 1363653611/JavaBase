package com.zbcn.GOF.factoryMothod.concrete;

import com.google.common.collect.Maps;
import com.zbcn.GOF.factoryMothod.framework.Factory;
import com.zbcn.GOF.factoryMothod.framework.Product;

import java.util.Map;
import java.util.Optional;

/**
 *  @title CarFactory
 *  @Description 创建汽车的具体工厂
 *  @author zbcn8
 *  @Date 2020/6/5 15:39
 */
public class CarFactory extends  Factory{

    private Map<String, Product> owners = Maps.newHashMap();

    @Override
    public Product createProduct(String owner) {
        Product product = owners.get(owner);
        product = Optional.ofNullable(product).orElse(new BMCar(owner));
        return product;
    }

    @Override
    public void registerProduct(Product product) {
        owners.put(((BMCar)product).getOwner(),product);
    }
}
