package com.zbcn.GOF.proxy.staticProxy.concrete;

import com.zbcn.GOF.proxy.staticProxy.iframework.Printable;

/**
 * 需要被代理的类
 */
public class Printer implements Printable {
    private String name;

    public Printer() {
        heavyJob("正在生成printer 打印实例。");
    }

    private void heavyJob(String s) {
        System.out.println(s);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束。。。");
    }

    @Override
    public void setPrinterName(String name) {
        this.name = name;
    }

    @Override
    public String getPrinterName() {
        return name;
    }

    @Override
    public void print(String str) {
        System.out.println("=====" + str + "=====");
        System.out.println(name);
    }
}
