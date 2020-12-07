package com.zbcn.test;

import lombok.Data;
import org.bouncycastle.crypto.OutputLengthException;

@Data
public class MyVector<T> {
    /**
     * 需要存储的数据
     */
    private Object[] data;

    /**
     * 存储数组中的元素个数
     */
    private int size;
    /**
     * 存储数组中可以容纳的最大的元素个数
     */
    private int capacity;

    public MyVector() {
        data = new Object[100];
        size = 0;
        this.capacity = 100;
    }

    private void resize(int newCapacity){
        Object[] newData = new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, capacity);
        data = newData;
        capacity = newCapacity;
    }
    //平均复杂度为 O(1)
    public void push(T o){
        if(size == capacity){
            resize(capacity*2);
        }
        data[size++] = o;
    }
    //平均复杂度为 O(1)
    public T pop(){
        if (size < 0){
            throw new OutputLengthException("已经没有数据");
        }
        return (T) data[--size];
    }

    public static void main(String[] args) {
        MyVector<Integer> myVector = new MyVector<>();
        for(int i = 0 ; i < 200; i++){
            myVector.push(i);
        }
        while (myVector.getSize() >0){
            System.out.println(myVector.pop());
        }
    }
}
