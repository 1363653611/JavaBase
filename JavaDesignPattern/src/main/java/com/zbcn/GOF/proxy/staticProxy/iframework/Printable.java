package com.zbcn.GOF.proxy.staticProxy.iframework;

/**
 * 代理对象 和被代理对象需要共同实现的接口
 */
public interface Printable {

    //设置名字
    void setPrinterName(String name);

     //获取打印名字
    String getPrinterName();

    //打印
    void print(String str);
}
