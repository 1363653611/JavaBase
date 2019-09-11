package com.zbcn.pattern.commond;

public class CommandOn implements Command {

	private Tv myTv;
	
	public CommandOn(Tv myTv) {
		super();
		this.myTv = myTv;
	}

	@Override
	public void execute() {
		myTv.turnOn();
		
	}

}
