package com.zbcn.pattern.factory.gcff;

import com.zbcn.pattern.factory.cppojo.BMW;
import com.zbcn.pattern.factory.cppojo.BMW523;

public class FactoryBMW523 implements FactoryBMW {

	public BMW createBMW() {
		return new BMW523();
	}
	

}
