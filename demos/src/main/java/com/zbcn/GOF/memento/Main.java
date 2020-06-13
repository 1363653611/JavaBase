package com.zbcn.GOF.memento;

import com.zbcn.GOF.memento.game.Gamer;
import com.zbcn.GOF.memento.game.Memento;

public class Main {

    public static void main(String[] args) {
        Gamer gamer = new Gamer(100);
        Memento memento = gamer.createMemento();
        for (int i = 0; i <100 ; i++) {
            System.out.println("====" + i);
            System.out.println("当前状态：" + gamer);

            gamer.beg();
            System.out.println("所持金钱为：" + gamer.getMoney() + "元");

            //决定如何处理快照
            if(gamer.getMoney() > memento.getMoney()){
                System.out.println("所持的金钱增多了，所以保存游戏当前状态");
                memento = gamer.createMemento();
            }else if(gamer.getMoney() < memento.getMoney()/2){
                System.out.println("所持金额减少了一半，因此回复以前状态");
                gamer.restoreMemento(memento);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
