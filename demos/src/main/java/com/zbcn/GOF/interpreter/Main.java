package com.zbcn.GOF.interpreter;

import com.zbcn.GOF.interpreter.concrete.AndExpression;
import com.zbcn.GOF.interpreter.concrete.OrExpression;
import com.zbcn.GOF.interpreter.concrete.TerminalExpression;
import com.zbcn.GOF.interpreter.framework.Expression;

public class Main {

    public static void main(String[] args) {
        Expression robert = new TerminalExpression("Robert");
        Expression john = new TerminalExpression("John");

        OrExpression orExpression = new OrExpression(robert, john);

        Expression julie = new TerminalExpression("Julie");
        Expression married = new TerminalExpression("Married");
        AndExpression andExpression = new AndExpression(julie, married);

        orExpression.interpret("where are you");
        andExpression.interpret("how are you");

        System.out.println("John is male? " + orExpression.interpret("John"));
        System.out.println("Julie is a married women? "
                + andExpression.interpret("Married Julie"));
    }
}
