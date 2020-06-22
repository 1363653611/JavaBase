package com.zbcn.GOF.visitor.concrete;

import com.google.common.collect.Lists;
import com.zbcn.GOF.visitor.framework.Entry;
import com.zbcn.GOF.visitor.framework.FileTreatMentException;
import com.zbcn.GOF.visitor.framework.Visitor;

import java.util.Iterator;
import java.util.List;

/**
 * 具体的文件夹类
 */
public class Directory extends Entry {

    private String name;

    private int size;

    public Directory(String name) {
        this.name = name;
    }

    private List<Entry> dir = Lists.newArrayList();

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getSize() {
        int size = 0;
        for (Entry entry : dir) {
            size += entry.getSize();

        }
        return size;
    }

    @Override
    public Entry add(Entry entry){
        dir.add(entry);
        return entry;
    }

    @Override
    public Iterator iterator() throws FileTreatMentException{
      return dir.iterator();
    }


    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
