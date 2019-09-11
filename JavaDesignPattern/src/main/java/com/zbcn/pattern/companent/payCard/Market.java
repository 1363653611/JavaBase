package com.zbcn.pattern.companent.payCard;

public abstract class Market {
	
	public String name;
	public abstract void add(Market m);
	
	public abstract void remove(Market m);
	
	public abstract void PayByCard();
}
