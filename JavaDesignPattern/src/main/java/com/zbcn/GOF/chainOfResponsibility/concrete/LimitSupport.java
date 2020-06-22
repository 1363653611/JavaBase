package com.zbcn.GOF.chainOfResponsibility.concrete;

import com.zbcn.GOF.chainOfResponsibility.framework.Support;
import com.zbcn.GOF.chainOfResponsibility.framework.Trouble;

/**
 * 问题编号小于limit 的编号给解决
 */
public class LimitSupport extends Support {

    private  int limit;

    public LimitSupport(String name, int limit) {
        super(name);
        this.limit = limit;
    }

    @Override
    protected boolean resolve(Trouble trouble) {
        if(trouble.getNumber() < limit){
            System.out.println("开始解决问题。。。。");
            return true;
        }
        return false;
    }
}
