package com.zbcn.pattern.visit;

public class Visit implements IVisitor {

	@Override
	public void visit(ConcreteElement1 el1) {
		 el1.doSomething();
		 System.err.println("相当于拓展一下功能看看");
	}

	@Override
	public void visit(ConcreteElement2 el2) {
		el2.doSomething();
	}

}
