package com.zbcn.pattern.factory.cxgc;

public class Customer {
	public static void main(String[] args){    
        //生产宝马320系列配件  
        FactoryBMW320 factoryBMW320 = new FactoryBMW320();    
        factoryBMW320.createEngine();  
        factoryBMW320.createAircondition();  
    } 

}
