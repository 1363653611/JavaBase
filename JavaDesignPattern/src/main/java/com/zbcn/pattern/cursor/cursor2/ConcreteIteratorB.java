package com.zbcn.pattern.cursor.cursor2;

import com.zbcn.pattern.cursor.Aggregate;
import com.zbcn.pattern.cursor.Iterator;

/**
 * 黑箱聚集与内禀迭代子
 *
 * @author
 * @create 2018-05-25 17:25
 **/
public class ConcreteIteratorB extends Aggregate {

    private Object[] objArray = null;

    public ConcreteIteratorB(Object[] objArray) {
        this.objArray = objArray;
    }

    @Override
    public Iterator createIterator() {
        return new ConcreteIterator();
    }



    /**
     * 具体子迭代类
     *
     * @author
     * @create 2018-05-25 16:07
     **/
    private class ConcreteIterator implements Iterator {

        /**
         * 内部索引：记录当前迭代到的索引位置
         */
        private int index = 0;
        /**
         * 记录当前聚集对象的大小
         */
        private int size = 0;

        public ConcreteIterator() {
            this.size = objArray.length;
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
            return objArray[index];
        }
    }
}
