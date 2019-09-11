package com.zbcn.pattern.decorator;

public class Decorator implements Human {
	
	private Human human;
	
	public Decorator(Human human) {  
        this.human = human;  
    } 

	@Override
	public void wearClothes() {
		human.wearClothes(); 
	}

	@Override
	public void walkToWhere() {
		human.walkToWhere();
	}

}
