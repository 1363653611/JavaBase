package com.zbcn.pattern.adapt;

public class Adapter extends Adaptee implements Target {

	public void request() {
		super.specificRequest();
	}

}
