package com.zbcn.pattern.companent.payCard;

import java.util.ArrayList;
import java.util.List;

public class MarketBranch extends Market {

	List<Market> list = new ArrayList<Market>();
	
	public MarketBranch(String s) {  
        super.name = s;  
    } 
	@Override
	public void add(Market m) {
		list.add(m);

	}

	@Override
	public void remove(Market m) {
		list.remove(m); 

	}

	@Override
	public void PayByCard() {
		System.out.println(name + "消费,积分已累加入该会员卡");
		for (Market m : list) {  
            m.PayByCard();  
        } 
	}

}
