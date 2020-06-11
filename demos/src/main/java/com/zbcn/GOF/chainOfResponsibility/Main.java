package com.zbcn.GOF.chainOfResponsibility;

import com.zbcn.GOF.chainOfResponsibility.concrete.LimitSupport;
import com.zbcn.GOF.chainOfResponsibility.concrete.NoSupport;
import com.zbcn.GOF.chainOfResponsibility.concrete.OddSupport;
import com.zbcn.GOF.chainOfResponsibility.concrete.SpecialSupport;
import com.zbcn.GOF.chainOfResponsibility.framework.Support;
import com.zbcn.GOF.chainOfResponsibility.framework.Trouble;

/**
 * 责任链设计模式
 */
public class Main {

    public static void main(String[] args) {

        NoSupport alice = new NoSupport("ALICE");
        LimitSupport bob = new LimitSupport("bob", 100);
        SpecialSupport charlie = new SpecialSupport("charlie", 20);
        OddSupport elmo = new OddSupport("Elmo");

        //创建责任链
        alice.setNext(bob).setNext(charlie).setNext(elmo);

        for (int i = 0; i < 101; i++) {
            alice.support(new Trouble(i, "问题：" + i));
        }


    }
}
