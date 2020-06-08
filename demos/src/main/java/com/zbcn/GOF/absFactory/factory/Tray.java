package com.zbcn.GOF.absFactory.factory;

import com.google.common.collect.Lists;

import java.util.List;

/**
 *  @title Tray
 *  @Description 抽象部件：表示含有 Link 和 Tray 的类
 *  @author zbcn8
 *  @Date 2020/6/8 9:33
 */
public abstract class Tray extends Item {

    private List<Item> tray = Lists.newArrayList();
    public Tray(String caption) {
        super(caption);
    }

    public List<Item> getTray() {
        return tray;
    }

    public void setTray(List<Item> tray) {
        this.tray = tray;
    }

    public void add(Item item){
        tray.add(item);
    }
}
