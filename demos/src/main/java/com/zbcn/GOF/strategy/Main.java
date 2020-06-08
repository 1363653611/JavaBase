package com.zbcn.GOF.strategy;

import com.zbcn.GOF.strategy.concrete.Hand;
import com.zbcn.GOF.strategy.concrete.ProbStrategy;
import com.zbcn.GOF.strategy.concrete.WinningStrategy;
import com.zbcn.GOF.strategy.framework.Player;

public class Main {

    public static void main(String[] args) {
        Player zhangsan = new Player("zhangsan", new WinningStrategy(3));
        Player lisi = new Player("lisi", new ProbStrategy(4));

        for (int i = 0; i < 100; i++) {
            Hand zs = zhangsan.nextHand();
            Hand ls = lisi.nextHand();
            if(zs.isStrongThan(ls)){
                System.out.println("张三胜: " + zhangsan);
                zhangsan.win();
                lisi.lose();
            }else if(ls.isStrongThan(zs)){
                System.out.println("李四胜:" + ls);
                lisi.win();
                zhangsan.lose();
            }else{
                System.out.println("平局");
                lisi.even();
                zhangsan.even();
            }
        }
    }
}
