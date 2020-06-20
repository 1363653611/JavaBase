package com.zbcn.GOF.proxy.staticProxy;

import com.zbcn.GOF.proxy.staticProxy.concrete.PrinterProxy;

public class Main {

    public static void main(String[] args) {
        PrinterProxy zhangsan = new PrinterProxy("zhangsan");
        zhangsan.print("测试。。。");
    }
}
