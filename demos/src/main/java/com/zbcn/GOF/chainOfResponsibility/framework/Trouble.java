package com.zbcn.GOF.chainOfResponsibility.framework;

/**
 *  @title Trouble
 *  @Description 具体的问题类
 *  @author zbcn8
 *  @Date 2020/6/11 11:48
 */
public class Trouble {

    /**
     * 问题的编号
     */
    private int number;

    /**
     * 问题的名字
     */
    private String name;

    public Trouble(int number, String name) {
        this.number = number;
        this.name = name;
    }

    /**
     * 获取问题的编号
     * @return
     */
    public int getNumber(){
        return this.number;
    }

    @Override
    public String toString(){
        return "[ TROUBLE" + this.name + " NUMBER:" +this.number + "]";
    }
}
