package com.zbcn.GOF.bridge.framework;

public class Display {

    /**
     * 实现display 具体功能的类
     */
    private DisplayImpl displayImpl;

    public Display(DisplayImpl displayImpl) {
        this.displayImpl = displayImpl;
    }

    public void open(){
        displayImpl.rawOpen();
    }

    public  void  print(){
        displayImpl.rawPrint();
    }

    public void close(){
        displayImpl.rawClose();
    }

    public final void display(){
        open();
        print();
        close();
    }
}
