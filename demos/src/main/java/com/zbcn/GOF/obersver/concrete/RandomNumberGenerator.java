package com.zbcn.GOF.obersver.concrete;

import com.zbcn.GOF.obersver.framework.NumberGenerator;

import java.util.Random;

public class RandomNumberGenerator extends NumberGenerator {


    private Random random = new Random();

    private int number;

    @Override
    public int getNumber() {
        return this.number;
    }

    @Override
    public void executor() {

        for (int i = 0; i < 20; i++) {
           number = random.nextInt(50);
           notifyObservers();
        }
    }
}
