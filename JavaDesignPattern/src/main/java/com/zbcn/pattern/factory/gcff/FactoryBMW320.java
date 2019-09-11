package com.zbcn.pattern.factory.gcff;

import com.zbcn.pattern.factory.cppojo.BMW;
import com.zbcn.pattern.factory.cppojo.BMW320;

public class FactoryBMW320 implements FactoryBMW {

	public BMW createBMW() {
		return new BMW320();
	}

}
