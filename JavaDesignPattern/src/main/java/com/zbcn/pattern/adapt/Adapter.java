package com.zbcn.pattern.adapt;

public class Adapter extends Adaptee implements Target {

	public void request() {
		System.out.println("接口适配:");
		super.specificRequest();
	}

}
