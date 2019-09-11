package com.zbcn.pattern.visit;

import java.util.List;

public class Client {
	public static void main(String[] args){    
        List<Element> list = ObjectCtruture.getList();    
        for(Element e: list){    
            e.accept(new Visit());    
        }    
    }    
}
