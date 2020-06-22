package com.zbcn.GOF.factoryMothod.framework;

public abstract class Factory {

    /**
     * 模板方法,创建具体产品
     * @param owner
     */
    public Product create(String owner){
        Product product = createProduct(owner);
        registerProduct(product);
        return product;
    }

    /**
     * 创建铲平的抽象类
     * @param owner
     * @return
     */
    public abstract Product createProduct(String owner);

    /**
     * 注册产品
     * @param product
     */
    public abstract  void registerProduct(Product product);
}
