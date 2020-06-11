package com.zbcn.GOF.chainOfResponsibility.concrete;

import com.zbcn.GOF.chainOfResponsibility.framework.Support;
import com.zbcn.GOF.chainOfResponsibility.framework.Trouble;

/**
 *  @title NoSupport
 *  @Description 不做任何处理的方法
 *  @author zbcn8
 *  @Date 2020/6/11 13:52
 */
public class NoSupport extends Support {

    public NoSupport(String name) {
        super(name);
    }

    @Override
    protected boolean resolve(Trouble trouble) {
        return false;
    }
}
