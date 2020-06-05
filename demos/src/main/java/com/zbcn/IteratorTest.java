package com.zbcn;

import com.google.common.collect.Lists;

import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.Iterator;

public class IteratorTest {

    public static void main(String[] args) {
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4);
        Iterator<Integer> iterator = integers.iterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();
            if(next == 1){
                iterator.remove();
            }
        }
        System.out.println(integers);
    }
}
