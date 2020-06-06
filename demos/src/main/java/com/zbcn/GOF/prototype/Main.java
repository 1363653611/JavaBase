package com.zbcn.GOF.prototype;

import com.zbcn.GOF.prototype.concrete.MessageBox;
import com.zbcn.GOF.prototype.concrete.UnderLinePen;
import com.zbcn.GOF.prototype.framework.Manager;
import com.zbcn.GOF.prototype.framework.Product;

/**
 * 原型模式测试
 */
public class Main {
    public static void main(String[] args) {
        //原型管理类
        Manager manager = new Manager();
        UnderLinePen underLine = new UnderLinePen("underLine");
        MessageBox messageBox = new MessageBox("messageBox");
        //注册
        manager.register("testUnderLine",underLine);
        manager.register("testMessageBox",messageBox);
        //创建（克隆）
        Product testUnderLine = manager.create("testUnderLine");
        testUnderLine.use("wo de ceshi");
        Product testMessageBox = manager.create("testMessageBox");
        testMessageBox.use("wokankna vox");




    }
}
