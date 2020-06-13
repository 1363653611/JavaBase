package com.zbcn.GOF.obersver.concrete;

import com.zbcn.GOF.obersver.framework.NumberGenerator;
import com.zbcn.GOF.obersver.framework.Observer;

public class DigitObserver implements Observer {
    @Override
    public void update(NumberGenerator numberGenerator) {
        System.out.println("DigitObserver:" + numberGenerator.getNumber());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
