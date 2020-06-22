package com.zbcn.GOF.composite.concrete;

import com.google.common.collect.Lists;
import com.zbcn.GOF.composite.framework.Entry;

import java.util.Iterator;
import java.util.List;

public class Directory extends Entry {

    /**
     * 文件夹的名称
     */
    private String name;

    /**
     * 目录条目集合
     */
    private List<Entry> directory = Lists.newArrayList();

    public Directory(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getSize() {
        int size = 0;
        Iterator<Entry> iterator = directory.iterator();
        while (iterator.hasNext()){
            Entry next = iterator.next();
            size += next.getSize();
        }
        return size;
    }

    @Override
    public void printList(String prefix) {
        System.out.println(prefix + "/" + this.name);
        Iterator<Entry> iterator = directory.iterator();
        while (iterator.hasNext()){
            Entry next = iterator.next();
            next.printList(prefix+ "/" + name);
        }
    }

    public Entry add(Entry entry){
        directory.add(entry);
        return entry;
    }
}
