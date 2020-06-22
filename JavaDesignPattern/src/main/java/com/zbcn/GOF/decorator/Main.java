package com.zbcn.GOF.decorator;

import com.zbcn.GOF.decorator.concrete.FullBorder;
import com.zbcn.GOF.decorator.concrete.SideBorder;
import com.zbcn.GOF.decorator.concrete.StringDisplay;

public class Main {

    public static void main(String[] args) {
        StringDisplay b1 = new StringDisplay("hell, GOF");
        SideBorder sideBorder = new SideBorder(b1, '#');
        FullBorder fullBorder = new FullBorder(sideBorder);
        b1.show();
        sideBorder.show();
        fullBorder.show();


    }
}
