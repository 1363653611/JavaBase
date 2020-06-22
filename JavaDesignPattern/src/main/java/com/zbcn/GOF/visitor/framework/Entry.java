package com.zbcn.GOF.visitor.framework;

import java.util.Iterator;

/**
 * 组合模式的框架类，增加了接受访问者访问接口
 */
public abstract class Entry implements Element {

    public abstract String getName();

    public abstract int getSize();

    /**
     * 增加目录条目
     * @param entry
     * @throws FileTreatMentException
     */
    public Entry add(Entry entry) throws FileTreatMentException{
        throw new FileTreatMentException();
    }

    /**
     * 生成迭代器
     * @return
     * @throws FileTreatMentException
     */
    public Iterator iterator() throws FileTreatMentException{
        throw new FileTreatMentException();
    }

    public String toString(){
        return getName() + " (" + getSize() + " )";
    }
}
