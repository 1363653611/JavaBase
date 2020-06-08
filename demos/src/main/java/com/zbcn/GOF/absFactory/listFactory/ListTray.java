package com.zbcn.GOF.absFactory.listFactory;

import com.zbcn.GOF.absFactory.factory.Item;
import com.zbcn.GOF.absFactory.factory.Tray;

import java.util.Iterator;

public class ListTray extends Tray {

    public ListTray(String caption) {
        super(caption);
    }

    @Override
    public String makeHTML() {
        StringBuilder builder = new StringBuilder();
        builder.append("<li>\\n");
        builder.append(getCaption() + "\\n");
        builder.append("<ul>\\n");
        Iterator<Item> iterator = getTray().iterator();
        while (iterator.hasNext()){
            Item next = (Item)iterator.next();
            builder.append(next.makeHTML());
        }
        builder.append("<\\ul>\\n");
        builder.append("<\\li>\\n");
        return builder.toString();
    }
}
