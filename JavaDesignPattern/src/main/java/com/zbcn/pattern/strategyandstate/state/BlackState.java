package com.zbcn.pattern.strategyandstate.state;

import java.awt.Color;

public class BlackState extends State {

	@Override
	public void handlepush(Context c) {
		System.out.println("变成红色");  
		c.setState(new RedState());
	}

	@Override
	public void handlepull(Context c) {
		// TODO Auto-generated method stub

	}

	@Override
	public Color getcolor() {
		// TODO Auto-generated method stub
		return null;
	}

}
