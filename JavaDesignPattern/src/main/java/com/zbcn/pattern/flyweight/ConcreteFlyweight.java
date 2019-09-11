package com.zbcn.pattern.flyweight;

/**
 * 具体实现类
 *
 * @author
 * @create 2018-05-25 14:24
 **/
public class ConcreteFlyweight extends Flyweight {

    private String string;

    public ConcreteFlyweight(String string) {
        this.string = string;
    }

    @Override
    public void operation() {
        System.out.println("Concrete---Flyweight : " + string);
    }
}
