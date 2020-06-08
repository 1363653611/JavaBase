package com.zbcn.GOF.bridge.concrete;

import com.zbcn.GOF.bridge.framework.DisplayImpl;

/**
 * 具体的实现
 */
public class StringDisplayImpl extends DisplayImpl {

    private String str;

    private int width;

    public StringDisplayImpl(String str, int width) {
        this.str = str;
        this.width = width;
    }

    @Override
    public void rawOpen() {
        printLine();
    }

    @Override
    public void rawPrint() {
        System.out.println("|  " + str + "   |");
    }

    @Override
    public void rawClose() {
        printLine();
    }

    private void printLine(){
        System.out.print("+");
        for (int i = 0; i < width; i++) {
            System.out.print("-");
        }
        System.out.println("+");
    }
}
