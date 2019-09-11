package com.zbcn.pattern.strategyandstate.state;

import java.awt.Color;

public class BlueState extends State {

	@Override
	public void handlepush(Context c) {
		System.out.println("变成绿色");
		c.setState(new GreenState());  
	}

	@Override
	public void handlepull(Context c) {
		System.out.println("变成红色");  
		c.setState(new RedState());

	}

	@Override
	public Color getcolor() {
		return Color.blue;

	}

}
