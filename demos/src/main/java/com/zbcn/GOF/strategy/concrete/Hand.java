package com.zbcn.GOF.strategy.concrete;

/**
 * 猜拳游戏中的手势类
 */
public class Hand {

    //表示石头
    public static final int HAND_VALUE_GUU = 0;

    //剪刀
    public static final int HAND_VALUE_CHO = 1;

    // 表示布
    public static final int HAND_VALUE_PAA = 2;

    /**
     * 具体的handValue 值
     */
    private int handValue;

    private static final Hand[] hand = {
            new Hand(HAND_VALUE_GUU),
            new Hand(HAND_VALUE_CHO),
            new Hand(HAND_VALUE_PAA),
    };

    private static final String[] name = {"石头","剪刀","布"};

    public Hand(int handValue){
        this.handValue = handValue;
    }

    public static Hand getHand(int handValue){
        return hand[handValue];
    }

    public boolean isStrongThan(Hand h){
        return fight(h) == 1;
    }

    public boolean isWeakThan(Hand h){
        return fight(h) == -1;
    }

    private int fight(Hand h) {
        if(this == h){
            return 0;
        }else if((this.handValue +1)%3 == h.handValue){
            return 1;
        }else {
            return -1;
        }
    }

    @Override
    public String toString(){
        return name[handValue];
    }


}
