package com.zbcn.pattern.strategyandstate.state;

import java.awt.Color;

public class RedState extends State {

	@Override
	public void handlepush(Context c) {
		System.out.println("变成绿色");  
		c.setState(new BlueState());

	}

	@Override
	public void handlepull(Context c) {
		System.out.println("变成黑色");  
		c.setState(new BlackState());
	}

	@Override
	public Color getcolor() {
		return Color.red;
	}

}
