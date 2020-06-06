package com.zbcn.GOF.builder.framework;

import lombok.Data;

/**
 * 建造者组装类
 */
@Data
public class Director {

    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public void write(){
        System.out.println("start... ...");
        builder.buildTitle("测试标题。。。");
        builder.buildContent("具体内容是，测试builder 模式");
    }


}
