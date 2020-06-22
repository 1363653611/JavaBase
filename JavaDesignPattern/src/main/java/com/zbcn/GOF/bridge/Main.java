package com.zbcn.GOF.bridge;

import com.zbcn.GOF.bridge.concrete.CountDisplay;
import com.zbcn.GOF.bridge.concrete.StringDisplayImpl;
import com.zbcn.GOF.bridge.framework.Display;

public class Main {

    public static void main(String[] args) {
        Display display = new Display(new StringDisplayImpl("测试打印", 3));
        display.display();
        CountDisplay countDisplay = new CountDisplay(new StringDisplayImpl("多功能测试打印", 3));
        countDisplay.display();
        countDisplay.multiDisplay(3);
    }
}
