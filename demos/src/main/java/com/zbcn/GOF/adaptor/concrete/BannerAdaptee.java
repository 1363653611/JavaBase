package com.zbcn.GOF.adaptor.concrete;

/**
 *  @title BannerAdaptee
 *  @Description 被适配的对象
 *  @author zbcn8
 *  @Date 2020/6/5 17:35
 */
public class BannerAdaptee {

    private String  name;

    public BannerAdaptee(String name) {
        this.name = name;
    }

    public void showWithPattern(){
        System.out.println("(" + name + ")");
    }

    public void showWithStar(){
        System.out.println("*" + name + "*");
    }


}
