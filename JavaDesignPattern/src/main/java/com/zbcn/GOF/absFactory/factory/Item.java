package com.zbcn.GOF.absFactory.factory;

/**
 *  @title Item
 *  @Description 抽象部件： 统一处理 link 和 Tray 的类
 *  @author zbcn8
 *  @Date 2020/6/8 9:31
 */
public abstract class Item {

    /**
     * 项目标题
     */
    private String caption;

    public Item(String caption) {
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * 创建html 的抽象方法
     * @return
     */
    public abstract String makeHTML();
}
