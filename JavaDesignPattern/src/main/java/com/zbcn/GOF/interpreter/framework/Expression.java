package com.zbcn.GOF.interpreter.framework;

/**
 *  @title Expression
 *  @Description 表达式接口
 *  @author zbcn8
 *  @Date 2020/6/21 11:16
 */
public interface Expression {

    boolean interpret(String context);
}
