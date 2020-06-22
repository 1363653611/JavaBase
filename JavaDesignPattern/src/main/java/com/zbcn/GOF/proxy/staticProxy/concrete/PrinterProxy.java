package com.zbcn.GOF.proxy.staticProxy.concrete;

import com.zbcn.GOF.proxy.staticProxy.iframework.Printable;

/**
 * 代理类
 */
public class PrinterProxy implements Printable {

    private String name;

    private Printable real;

    public PrinterProxy() {
    }

    public PrinterProxy(String name) {
        this.name = name;
    }

    @Override
    public void setPrinterName(String name) {
        if(real != null){
            real.setPrinterName(name);
        }
        this.name = name;
    }

    @Override
    public String getPrinterName() {
        return name;
    }

    @Override
    public void print(String str) {
        realize();
        real.print(str);
    }

    private synchronized void realize() {
        if(real == null){
            real = new Printer();
            real.setPrinterName(name);
        }
    }
}
