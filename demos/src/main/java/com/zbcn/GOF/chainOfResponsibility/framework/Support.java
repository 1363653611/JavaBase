package com.zbcn.GOF.chainOfResponsibility.framework;

import java.util.Objects;

/**
 *  @title Support
 *  @Description 用来解决问题的抽象类， 责任链上的对象
 *  @author zbcn8
 *  @Date 2020/6/11 13:37
 */
public abstract class Support {

    /**
     * 解决问题对象的名字
     */
    private String name;

    /**
     * 下一个要解决问题的对象
     */
    private Support next;

    public Support(String name) {
        this.name = name;
    }

    /**
     * 设置下一个要解决问题的对象
     * @param next
     * @return
     */
    public  Support setNext(Support next){
        this.next = next;
        return next;
    }

    /**
     * 解决问题的步骤
     * @param trouble
     */
    public final void support(Trouble trouble){
        if(resolve(trouble)){
            doneTrouble(trouble);
        }else if(Objects.nonNull(next)){
            next.support(trouble);
        }else{
            fail(trouble);
        }

    }

    /**
     * 问题未解决
     * @param trouble
     */
    protected void fail(Trouble trouble){
        System.out.println(trouble + "not resolved.");
    }

    /**
     * 问题已经解决
     * @param trouble
     */
    public void doneTrouble(Trouble trouble){
        System.out.println(trouble + "is resolved by " + this + ".");
    };

    /**
     * 解决问题的方法
     * @param trouble
     * @return
     */
    protected abstract boolean resolve(Trouble trouble);
}
