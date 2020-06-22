package com.zbcn.GOF.chainOfResponsibility.concrete;

import com.zbcn.GOF.chainOfResponsibility.framework.Support;
import com.zbcn.GOF.chainOfResponsibility.framework.Trouble;

/**
 * 解决奇数编号的问题
 */
public class OddSupport extends Support {

    public OddSupport(String name) {
        super(name);
    }

    @Override
    protected boolean resolve(Trouble trouble) {
        if(trouble.getNumber() % 2 == 1){
            System.out.println("解决奇数编号问题：");
            return true;
        }
        return false;
    }
}
