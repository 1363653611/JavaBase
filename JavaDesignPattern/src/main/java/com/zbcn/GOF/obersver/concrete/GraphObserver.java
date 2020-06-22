package com.zbcn.GOF.obersver.concrete;

import com.zbcn.GOF.obersver.framework.NumberGenerator;
import com.zbcn.GOF.obersver.framework.Observer;

public class GraphObserver implements Observer {
    @Override
    public void update(NumberGenerator numberGenerator) {
        System.out.println("GraphObserver:");
        int number = numberGenerator.getNumber();
        for (int i = 0; i < number; i++) {
            System.out.println("*");
        }
        System.out.println("");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
