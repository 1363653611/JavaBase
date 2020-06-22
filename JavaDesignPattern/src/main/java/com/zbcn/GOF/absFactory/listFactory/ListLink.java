package com.zbcn.GOF.absFactory.listFactory;

import com.zbcn.GOF.absFactory.factory.Link;

public class ListLink extends Link {
    public ListLink(String caption, String url) {
        super(caption, url);
    }

    @Override
    public String makeHTML() {
        return "<li><a href = '\\"+getUrl()+">" + getCaption() + "<\\a><\\li>\\n";
    }
}
