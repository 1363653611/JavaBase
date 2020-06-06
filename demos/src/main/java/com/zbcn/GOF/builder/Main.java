package com.zbcn.GOF.builder;

import com.zbcn.GOF.builder.concrete.HtmlBuilder;
import com.zbcn.GOF.builder.concrete.TextBuilder;
import com.zbcn.GOF.builder.framework.Director;

public class Main {
    public static void main(String[] args) {
        TextBuilder textBuilder = new TextBuilder();
        HtmlBuilder htmlBuilder = new HtmlBuilder();
        Director director = new Director(textBuilder);
        director.write();

        director.setBuilder(htmlBuilder);
        director.write();
    }
}
