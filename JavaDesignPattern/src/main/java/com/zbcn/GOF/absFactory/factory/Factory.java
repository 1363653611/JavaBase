package com.zbcn.GOF.absFactory.factory;

/**
 *  @title Factory
 *  @Description 表示抽象工厂的类
 *  @author zbcn8
 *  @Date 2020/6/8 9:30
 */
public abstract class Factory {

    public static Factory getFactory(String className){

        Factory factory = null;
        try {
            factory = (Factory)Class.forName(className).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return factory;
    }

    public abstract  Link creatLink(String caption, String url);
    public abstract  Tray creatTray(String caption);
    public abstract  Page creatPage(String title, String author);
}
