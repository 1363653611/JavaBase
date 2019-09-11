package com.zbcn.pattern.cursor.cursor1;

import com.zbcn.pattern.cursor.Aggregate;
import com.zbcn.pattern.cursor.Iterator;

/**
 * 具体的聚集角色类
 *
 * @author
 * @create 2018-05-25 15:42
 **/
public class ConcreteAggregate extends Aggregate {

    private Object[] objArray = null;

    public ConcreteAggregate(Object[] objArray) {
        this.objArray = objArray;
    }

    @Override
    public Iterator createIterator() {
        return new ConcreteIterator(this);
    }

    /**
     * 给外界提供取值的方法
     */
    public Object getElement(int index) {
        if (index < objArray.length) {
            return objArray[index];
        } else {
            return null;
        }
    }

    /**
     * 给外界提供值的大小
     * @return
     */
    public int size () {
        return objArray.length;
    }
}
