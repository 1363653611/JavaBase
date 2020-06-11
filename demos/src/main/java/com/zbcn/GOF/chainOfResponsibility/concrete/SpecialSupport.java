package com.zbcn.GOF.chainOfResponsibility.concrete;

import com.zbcn.GOF.chainOfResponsibility.framework.Support;
import com.zbcn.GOF.chainOfResponsibility.framework.Trouble;

/**
 *  @title SpecialSupport
 *  @Description 解决指定编号的问题
 *  @author zbcn8
 *  @Date 2020/6/11 13:58
 */
public class SpecialSupport extends Support {

    private int number;

    public SpecialSupport(String name, int number) {
        super(name);
        this.number = number;
    }

    @Override
    protected boolean resolve(Trouble trouble) {
        if(trouble.getNumber() == this.number){
            System.out.println("resolved special number trouble" + number);
            return true;
        }
        return false;
    }
}
