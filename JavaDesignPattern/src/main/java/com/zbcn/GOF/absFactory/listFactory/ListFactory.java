package com.zbcn.GOF.absFactory.listFactory;

import com.zbcn.GOF.absFactory.factory.Factory;
import com.zbcn.GOF.absFactory.factory.Link;
import com.zbcn.GOF.absFactory.factory.Page;
import com.zbcn.GOF.absFactory.factory.Tray;

/**
 *  @title ListFactory
 *  @Description 具体的工厂类
 *  @author zbcn8
 *  @Date 2020/6/8 10:08
 */
public class ListFactory extends Factory {
    @Override
    public Link creatLink(String caption, String url) {
        return new ListLink(caption,url);
    }

    @Override
    public Tray creatTray(String caption) {
        return new ListTray(caption);
    }

    @Override
    public Page creatPage(String title, String author) {
        return new ListPage(title,author);
    }
}
