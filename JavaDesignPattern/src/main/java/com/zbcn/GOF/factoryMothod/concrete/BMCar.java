package com.zbcn.GOF.factoryMothod.concrete;

import com.zbcn.GOF.factoryMothod.framework.Product;

public class BMCar extends Product {

    private String  owner;

    public BMCar(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public void use() {
        System.out.println(owner + "use BM car");
    }
}
