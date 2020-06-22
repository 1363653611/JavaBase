package com.zbcn.GOF.decorator.concrete;

import com.zbcn.GOF.decorator.framework.Border;
import com.zbcn.GOF.decorator.framework.Display;

public class FullBorder extends Border {


    public FullBorder(Display display) {
        super(display);
    }

    @Override
    public int getColumns() {
        return 1 + display.getColumns() + 1;
    }

    @Override
    public int getRows() {
        return 1 + display.getRows() + 1;
    }

    @Override
    public String getRowText(int row) {
        if (row == 0) {//上边框
            return "+" + makeLine("-", display.getColumns()) + "+";
        } else if (row == display.getRows() + 1) {//上边框
            return "+" + makeLine("-", display.getColumns()) + "+";
        } else {//其他边框
            return "|" + display.getRowText(row) + "|";
        }

    }

    private String makeLine(String s, int columns) {

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < columns; i++) {
            builder.append(s);
        }
        return builder.toString();
    }
}
