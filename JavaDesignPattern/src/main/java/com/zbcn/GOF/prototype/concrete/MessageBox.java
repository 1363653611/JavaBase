package com.zbcn.GOF.prototype.concrete;

import com.zbcn.GOF.prototype.framework.Product;

public class MessageBox implements Product {

    private String decochar;

    public MessageBox(String decochar) {
        this.decochar = decochar;
    }

    @Override
    public void use(String s) {
        int length = s.getBytes().length;
        System.out.println("*******");
        for (int i = 0; i <length + 4 ; i++) {
            System.out.println(decochar);
        }
        System.out.println("");
        System.out.println(decochar + " " + s + " " + decochar);
        for (int i = 0; i < length+ 4; i++) {
            System.out.println(decochar);
        }
        System.out.println("");
    }

    @Override
    public Product createClone() {
        try {
            return (Product)clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
