package com.zbcn.pattern.strategyandstate.strategy;

public class discountA implements IStrategy {

	@Override
	public double countMoney(double money) {
		// TODO Auto-generated method stub
		return money * 0.8;
	}

}
