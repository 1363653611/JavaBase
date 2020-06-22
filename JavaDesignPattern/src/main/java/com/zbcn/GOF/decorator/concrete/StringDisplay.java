package com.zbcn.GOF.decorator.concrete;

import com.zbcn.GOF.decorator.framework.Display;

/**
 * @author zbcn8
 * @title StringDisplay
 * @Description 字符串展示类(需要装饰的核心类)
 * @Date 2020/6/10 13:41
 */
public class StringDisplay extends Display {

    private String str;

    public StringDisplay(String str) {
        this.str = str;
    }

    @Override
    public int getColumns() {
        return str.getBytes().length;
    }

    @Override
    public int getRows() {
        return 1;
    }

    @Override
    public String getRowText(int row) {
        return str;
    }

}
