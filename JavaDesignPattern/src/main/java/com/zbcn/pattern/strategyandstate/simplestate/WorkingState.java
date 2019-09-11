package com.zbcn.pattern.strategyandstate.simplestate;

public class WorkingState implements IState{

	@Override
	public void submit(FileSub file) {
		Console.WriteLine("working-------------");
		 file.setState(new EndState());
	}

}
