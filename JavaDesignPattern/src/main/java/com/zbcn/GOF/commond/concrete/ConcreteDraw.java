package com.zbcn.GOF.commond.concrete;

import com.zbcn.GOF.commond.framework.Drawable;

/**
 *  @title ConcreteDraw
 *  @Description 具体的操作类
 *  @author zbcn8
 *  @Date 2020/6/21 10:08
 */
public class ConcreteDraw implements Drawable {
    @Override
    public void draw(String str) {
        System.out.println("画的内容是："+str);
    }
}
