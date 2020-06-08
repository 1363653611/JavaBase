package com.zbcn.GOF.strategy.framework;

import com.zbcn.GOF.strategy.concrete.Hand;

/**
 * 策略的抽象类
 */
public interface Strategy {

    /**
     * 下一个手势
     * @return
     */
    public abstract Hand nextHand();

    /**
     * 学习
     * @param win
     */
    public abstract void study(boolean win);
}
