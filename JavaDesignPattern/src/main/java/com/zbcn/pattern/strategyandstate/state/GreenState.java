package com.zbcn.pattern.strategyandstate.state;

import java.awt.Color;

public class GreenState extends State {

	@Override
	public void handlepush(Context c) {
		 System.out.println("变成黑色");
		 c.setState(new BlackState()); 
	}

	@Override
	public void handlepull(Context c) {
		 System.out.println("变成蓝色");  
		c.setState(new BlueState()); 
	}

	@Override
	public Color getcolor() {
		// TODO Auto-generated method stub
		return Color.green;
	}

}
