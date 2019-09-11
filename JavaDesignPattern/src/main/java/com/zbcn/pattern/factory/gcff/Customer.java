package com.zbcn.pattern.factory.gcff;

import com.zbcn.pattern.factory.cppojo.BMW320;
import com.zbcn.pattern.factory.cppojo.BMW523;

public class Customer {
	public static void main(String[] args) {  
        FactoryBMW320 factoryBMW320 = new FactoryBMW320();  
        BMW320 bmw320 = (BMW320) factoryBMW320.createBMW();
  
        FactoryBMW523 factoryBMW523 = new FactoryBMW523();  
        BMW523 bmw523 = (BMW523) factoryBMW523.createBMW();
    }  
}
