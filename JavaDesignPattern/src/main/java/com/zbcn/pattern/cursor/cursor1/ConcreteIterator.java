package com.zbcn.pattern.cursor.cursor1;

import com.zbcn.pattern.cursor.Iterator;

/**
 * 具体子迭代类
 *
 * @author
 * @create 2018-05-25 16:07
 **/
public class ConcreteIterator implements Iterator {
    /**
     * 持有被迭代的具体聚合对象
     */
    private ConcreteAggregate agg;
    /**
     * 内部索引：记录当前迭代到的索引位置
     */
    private int index = 0;
    /**
     * 记录当前聚集对象的大小
     */
    private int size = 0;

    public ConcreteIterator(ConcreteAggregate agg) {
        this.agg = agg;
        this.size = agg.size();
        index = 0;
    }


    @Override
    public void first() {
        index = 0;
    }

    @Override
    public void next() {
        if(index < size) {
            index ++;
        }
    }

    @Override
    public boolean isDone() {
        return (index >= size);
    }

    @Override
    public Object currentObject() {
        return agg.getElement(index);
    }
}
