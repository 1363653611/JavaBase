package com.zbcn.pattern.mediator;

public class Client {

	public static void main(String[] args) {
		AbstractColleague A = new ColleagueA();
		AbstractColleague B = new ColleagueB();
		AbstractMediator am = new Mediator(A,B);
		
		System.out.println("==========通过设置A影响B==========");    
	    A.setNumber(1000, am);    
	    System.out.println("collA的number值为："+A.getNumber());    
	    System.out.println("collB的number值为A的10倍："+B.getNumber());    

	    System.out.println("==========通过设置B影响A==========");    
	    B.setNumber(1000, am);    
	    System.out.println("collB的number值为："+B.getNumber());    
	    System.out.println("collA的number值为B的0.1倍："+A.getNumber());  
	}
}
