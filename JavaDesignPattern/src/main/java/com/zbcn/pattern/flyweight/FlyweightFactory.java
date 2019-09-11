package com.zbcn.pattern.flyweight;

import java.util.Hashtable;

/**
 * 工厂方法
 *
 * @author
 * @create 2018-05-25 14:28
 *
 **/
public class FlyweightFactory {

    private Hashtable flyweights = new Hashtable();

    public FlyweightFactory() {
    }

    /**
     * 1.定义了一个Hashtable用来存储各个对象
     * 2.在选出要实例化的对象，将该对象返回
     * 3.如果在Hashtable中没有要选择的对象，此时变量flyweight为null，
     * 产生一个新的flyweight存储在Hashtable中，并将该对象返回
     * @param obj
     * @return
     */
    public Flyweight getFlyweight(Object obj){
        Flyweight flyweight = (Flyweight) flyweights.get(obj);
        if(flyweight == null){
            flyweight = new ConcreteFlyweight((String)obj);
            flyweights.put(obj, flyweight);
        }
        return flyweight;
    }


    public int getFlyweightSize(){
        return flyweights.size();
    }
}
