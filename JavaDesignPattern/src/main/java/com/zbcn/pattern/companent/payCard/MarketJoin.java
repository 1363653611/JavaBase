package com.zbcn.pattern.companent.payCard;

public class MarketJoin extends Market {

	public MarketJoin(String s) {  
        super.name = s;  

    }  
	@Override
	public void add(Market m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(Market m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void PayByCard() {
		System.out.println(name + "消费,积分已累加入该会员卡");

	}

}
