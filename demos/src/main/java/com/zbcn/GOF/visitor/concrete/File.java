package com.zbcn.GOF.visitor.concrete;

import com.zbcn.GOF.visitor.framework.Entry;
import com.zbcn.GOF.visitor.framework.Visitor;

public class File extends Entry {

    private String name;
    private  int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
