package com.zbcn.GOF.iterator;

import com.zbcn.GOF.iterator.concrete.BookShelf;
import com.zbcn.GOF.iterator.entity.Book;
import com.zbcn.GOF.iterator.interfaces.Iterator;

/**
 *  @title Main
 *  @Description 迭代器模式测试客户端
 *  @author zbcn8
 *  @Date 2020/6/5 17:08
 */
public class Main {
    public static void main(String[] args) {
        BookShelf bookShelf = new BookShelf();
        bookShelf.appendBook(new Book("测试"));
        bookShelf.appendBook(new Book("看书"));
        bookShelf.appendBook(new Book("抢红包"));

        Iterator iterator = bookShelf.iterator();
        while (iterator.hasNext()){
            System.out.println(((Book)iterator.next()).getName());
        }
    }
}
