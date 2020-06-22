package com.zbcn.GOF.absFactory;

import com.zbcn.GOF.absFactory.factory.Factory;
import com.zbcn.GOF.absFactory.factory.Link;
import com.zbcn.GOF.absFactory.factory.Page;
import com.zbcn.GOF.absFactory.factory.Tray;

/**
 *  @title Main
 *  @Description 抽象工厂测试类
 *  @author zbcn8
 *  @Date 2020/6/8 9:27
 */
public class Main {
    public static void main(String[] args) {
        Factory factory = Factory.getFactory("com.zbcn.GOF.absFactory.listFactory.ListFactory");
        Link baidu = factory.creatLink("baidu", "www.baidu.com");
        Link bzmh = factory.creatLink("暴走漫画", "wwww.baozou.com");

        Tray tray = factory.creatTray("工作杂志");
        tray.add(baidu);
        tray.add(bzmh);

        Page page = factory.creatPage("linkPage", "zbcn");
        page.add(tray);

        page.outPut();
    }
}
