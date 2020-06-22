package com.zbcn.GOF.obersver.framework;

import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

/**
 *  @title NumberGenerator
 *  @Description 生成数值的抽象类(被观察者 subject)
 *  @author zbcn8
 *  @Date 2020/6/13 13:24
 */
public abstract class NumberGenerator {

    private List<Observer> observers = Lists.newArrayList();

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public void deleteObserver(Observer observer){
        observers.remove(observer);
    }

    /**
     * 通知观察者的方法
     */
    public void notifyObservers(){
        Iterator<Observer> it = observers.iterator();
        while (it.hasNext()){
            Observer next = it.next();
            next.update(this);
        }
    }

    public abstract int getNumber();

    public abstract void executor();


}
