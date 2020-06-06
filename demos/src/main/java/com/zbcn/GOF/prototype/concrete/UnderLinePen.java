package com.zbcn.GOF.prototype.concrete;

import com.zbcn.GOF.prototype.framework.Product;

public class UnderLinePen implements Product {

    private String ulchar;

    public UnderLinePen(String ulchar) {
        this.ulchar = ulchar;
    }

    @Override
    public void use(String s) {
        int length = s.getBytes().length;
        System.out.println("___\" "+ s + " \"___");
        for (int i = 0; i <length ; i++) {
            System.out.println(ulchar);
        }
        System.out.println("");
        System.out.println(ulchar + "___\" " + s + " \"___s" + ulchar);
        for (int i = 0; i < length+ 4; i++) {
            System.out.println(ulchar);
        }
        System.out.println("");
    }

    @Override
    public Product createClone() {
        try {
            return (Product) clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
