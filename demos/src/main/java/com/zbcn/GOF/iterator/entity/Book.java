package com.zbcn.GOF.iterator.entity;

/**
 *  @title Book
 *  @Description 实体类
 *  @author zbcn8
 *  @Date 2020/6/5 16:49
 */
public class Book {

    private String name;


    public Book(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
