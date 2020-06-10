package com.zbcn.GOF.decorator.concrete;

import com.zbcn.GOF.decorator.framework.Border;
import com.zbcn.GOF.decorator.framework.Display;

/**
 *  @title SideBorder
 *  @Description 具体的装饰框架
 *  @author zbcn8
 *  @Date 2020/6/10 19:10
 */
public class SideBorder extends Border {

    /**
     * 装饰边框的字符
     */
    private char borderChar;

    public SideBorder(Display display, char borderChar) {
        super(display);
        this.borderChar = borderChar;
    }

    /**
     * 字符数为字符数 + 两侧边框的字符数
     * @return
     */
    @Override
    public int getColumns() {
        return 1 + display.getColumns() + 1;
    }

    /**
     * 备装饰物的行数
     * @return
     */
    @Override
    public int getRows() {
        return display.getRows();
    }

    /**
     * 指定的哪一行的字符串为被装饰物的字符串 + 两侧边框的字符
     * @param row
     * @return
     */
    @Override
    public String getRowText(int row) {
        return borderChar + display.getRowText(row) + borderChar;
    }
}
