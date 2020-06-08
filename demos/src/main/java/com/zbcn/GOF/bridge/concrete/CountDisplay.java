package com.zbcn.GOF.bridge.concrete;

import com.zbcn.GOF.bridge.framework.Display;
import com.zbcn.GOF.bridge.framework.DisplayImpl;

/**
 * 功能性display， 增加新功能
 */
public class CountDisplay extends Display {

    public CountDisplay(DisplayImpl displayImpl){
        super(displayImpl);
    }

    /**
     * 新增加的功能：多次打印
     * @param times
     */
    public void multiDisplay(int times){
        open();
        for (int i = 0; i < times; i++) {
            print();
        }
        close();
    }
}
