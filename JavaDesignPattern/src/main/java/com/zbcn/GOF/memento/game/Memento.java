package com.zbcn.GOF.memento.game;

import com.google.common.collect.Lists;

import java.util.List;

/**
 *  @title Memento
 *  @Description 主人公状态的类
 *  @author zbcn8
 *  @Date 2020/6/13 15:05
 */
public class Memento {

    /**
     * 所持有的金钱
     */
    int money;

    /**
     * 获得到的水果
     */
    List fruits;

    Memento(int money){
        this.money = money;
        this.fruits = Lists.newArrayList();
    }

    public int getMoney() {
        return money;
    }

    /**
     * 添加水果
     * @param fruit
     */
    void addFruit(String fruit){
        fruits.add(fruit);
    }

    /**
     * 获得当前所有水果
     * @return
     */
    List getFruits(){
        return Lists.newCopyOnWriteArrayList(this.fruits);
    }
}
