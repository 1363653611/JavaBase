package com.zbcn.pattern.cursor;

import com.zbcn.pattern.cursor.cursor1.ConcreteAggregate;
import com.zbcn.pattern.cursor.cursor2.ConcreteIteratorB;

/**
 * 测试工具类：首先创建了一个聚集类实例，然后调用聚集对象的工厂方法createIterator()以得到一个迭代子对象。
 * 在得到迭代子的实例后，客户端开始迭代过程，打印出所有的聚集元素
 *
 * @author
 * @create 2018-05-25 16:16
 **/
public class Client {

    public void operationA(){
        Object[] objArray = {"One","Two","Three","Four","Five","Six"};
        //创建聚合对象:相当与在迭代其内部做一个迭代器
        Aggregate agg = new ConcreteAggregate(objArray);
        Iterator iterator = agg.createIterator();
        while (!iterator.isDone()){
            System.out.println(iterator.currentObject());
            iterator.next();
        }
    }

    public void operationB() {
        Object[] objArray = {"One","Two","Three","Four","Five","Six"};
        ConcreteIteratorB agg = new ConcreteIteratorB(objArray);
        Iterator iterator = agg.createIterator();
        while (!iterator.isDone()){
            System.out.println(iterator.currentObject());
            iterator.next();
        }
    }

    public static void main(String[] args) {

        Client client = new Client();
        client.operationA();
        client.operationB();
    }
}
