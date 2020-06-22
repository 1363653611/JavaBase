package com.zbcn.GOF.obersver;

import com.zbcn.GOF.obersver.concrete.DigitObserver;
import com.zbcn.GOF.obersver.concrete.GraphObserver;
import com.zbcn.GOF.obersver.concrete.RandomNumberGenerator;
import com.zbcn.GOF.obersver.framework.NumberGenerator;

/**
 * 观察者设计模式
 */
public class Main {

    public static void main(String[] args) {
        NumberGenerator generator = new RandomNumberGenerator();

        DigitObserver digitObserver = new DigitObserver();
        GraphObserver graphObserver = new GraphObserver();

        generator.addObserver(digitObserver);
        generator.addObserver(graphObserver);
        generator.executor();
    }
}
