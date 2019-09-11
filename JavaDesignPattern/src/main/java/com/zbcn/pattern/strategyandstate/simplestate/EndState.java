package com.zbcn.pattern.strategyandstate.simplestate;

public class EndState implements IState {

	@Override
	public void submit(FileSub file) {
		Console.WriteLine("end------------");
		file.setState(new BeginState());
	}

}
