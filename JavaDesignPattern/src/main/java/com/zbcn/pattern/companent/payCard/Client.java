package com.zbcn.pattern.companent.payCard;

public class Client {
	public static void main(String[] args) {  
          
        MarketBranch rootBranch = new MarketBranch("总店");  
        MarketBranch qhdBranch = new MarketBranch("秦皇岛分店");  
        MarketJoin hgqJoin = new MarketJoin("秦皇岛分店一海港区加盟店");  
        MarketJoin btlJoin = new MarketJoin("秦皇岛分店二白塔岭加盟店");  
          
        qhdBranch.add(hgqJoin);  
        qhdBranch.add(btlJoin);  
        rootBranch.add(qhdBranch);  
        rootBranch.PayByCard();  
    }  
}
