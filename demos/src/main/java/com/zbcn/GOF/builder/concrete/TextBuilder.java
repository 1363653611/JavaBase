package com.zbcn.GOF.builder.concrete;

import com.zbcn.GOF.builder.framework.Builder;

public class TextBuilder implements Builder {
    @Override
    public void buildTitle(String title) {
        System.out.println("普通文本输出:" + title);
    }

    @Override
    public void buildContent(String content) {

        System.out.println("普通内容数据:" + content);
    }
}
