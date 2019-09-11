package com.zbcn.pattern.strategyandstate.simplestate;

public class BeginState implements IState {

	@Override
	public void submit(FileSub file) {
		Console.WriteLine("begin----");
		file.setState(new WorkingState());

	}

}
