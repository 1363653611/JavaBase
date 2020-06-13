package com.zbcn.GOF.memento.game;

import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 *  @title Gamer
 *  @Description 表示游戏主人公类
 *  @author zbcn8
 *  @Date 2020/6/13 15:08
 */
public class Gamer {

    //所持金钱
    private int money;

    private List<String> fruits = Lists.newArrayList();

    private Random random = new Random();

    private String[] fruitsName = {"苹果","葡萄","香蕉","橘子"};

    public Gamer(int money){
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void beg(){
        int dice = random.nextInt(6) +1;
        if(dice == 1){
            money += 100;
            System.out.println("所持金钱增加了。");
        }else if(dice == 2){
            money/=2;
            System.out.println("所持金钱减半。。");
        }else if(dice == 6){
            String fruit = getFruit();
            System.out.println("获得了水果：" + fruit);
            fruits.add(fruit);
        }else {
            System.out.println("生么都没有发生。。。");
        }
    }

    private String getFruit() {
        String prefix = "";
        if(random.nextBoolean()){
            prefix = "good";
        }
        return prefix + fruitsName[random.nextInt(fruitsName.length)];
    }

    /**
     * 创建快照
     */
    public Memento createMemento(){
        Memento memento = new Memento(this.money);
        Iterator<String> iterator = fruits.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            if(next.startsWith("good")){
                memento.addFruit(next);
            }
        }
        return memento;
    }

    /**
     * 撤销
     * @param memento
     */
    public void restoreMemento(Memento memento){
        this.money = memento.money;
        this.fruits = memento.getFruits();
    }


    public String toString(){
        return "[money = " + money + ", fruits = " + this.fruits + "]";
    }
}
