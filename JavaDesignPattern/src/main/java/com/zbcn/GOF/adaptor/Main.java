package com.zbcn.GOF.adaptor;

import com.zbcn.GOF.adaptor.concrete.PrintBannerAdapter;
import com.zbcn.GOF.adaptor.interfaces.Print;

/**
 *  @title Main
 *  @Description 适配器模式客户端
 *  @author zbcn8
 *  @Date 2020/6/5 17:42
 */
public class Main {

    public static void main(String[] args) {
        Print adapter = PrintBannerAdapter.buildAdapter("测试机哦");
        adapter.printStrong();
        adapter.printWeak();
    }
}
