package com.zbcn.pattern.strategyandstate.strategy;


public class MainStrategys {

	public static void mainStrategy(){
		Strategy strategt;
		 discountA da = new discountA();
		 strategt = new Strategy(da);
		 System.out.println("A打折策略--"+strategt.countMoney(111));
		 
		 discountB db = new discountB();
		 strategt = new Strategy(db);
		 System.out.println("B打折策略--" + strategt.countMoney(111));
	}
	
	public static void main(String[] args) {
		mainStrategy();
	}
}
