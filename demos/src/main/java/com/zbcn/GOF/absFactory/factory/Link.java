package com.zbcn.GOF.absFactory.factory;

/**
 *  @title Link
 *  @Description 抽象部件：表示html link 链接的类
 *  @author zbcn8
 *  @Date 2020/6/8 9:32
 */
public abstract class Link  extends Item{
    private String url;
    public Link(String caption,String url) {
        super(caption);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
