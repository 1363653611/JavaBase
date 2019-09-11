package com.zbcn.pattern.strategyandstate.strategy;

public class discountB implements IStrategy {

	@Override
	public double countMoney(double money) {
		return money - ((int)(money / 100) * 20);
	}

}
