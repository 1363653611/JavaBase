package com.zbcn.GOF.interpreter.concrete;

import com.zbcn.GOF.interpreter.framework.Expression;

/**
 *  @title TerminalExpression
 *  @Description 表达式的实现类
 *  @author zbcn8
 *  @Date 2020/6/21 11:18
 */
public class TerminalExpression implements Expression {

    private String data;

    public TerminalExpression(String data) {
        this.data = data;
    }

    @Override
    public boolean interpret(String context) {
        if(context.contains(data)){
            return true;
        }
        return false;
    }
}
