package com.zbcn.GOF.builder.concrete;

import com.zbcn.GOF.builder.framework.Builder;

public class HtmlBuilder implements Builder {
    @Override
    public void buildTitle(String title) {
        System.out.println("<title>" + title + "</title>");
    }

    @Override
    public void buildContent(String content) {
        System.out.println("<div>" + content + "</divs>");
    }
}
