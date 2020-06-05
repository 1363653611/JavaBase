package com.zbcn.GOF.iterator.concrete;

import com.google.common.collect.Lists;
import com.zbcn.GOF.iterator.entity.Book;
import com.zbcn.GOF.iterator.interfaces.Aggregator;
import com.zbcn.GOF.iterator.interfaces.Iterator;

import java.util.List;

/**
 *  @title BookShelf
 *  @Description 具体的聚合类
 *  @author zbcn8
 *  @Date 2020/6/5 16:50
 */
public class BookShelf implements Aggregator {

    private List<Book> books;

    public BookShelf() {
        this.books = Lists.newArrayList();
    }

    @Override
    public Iterator iterator() {
        return new BookShelfIterator(this);
    }

    public Book getBootAt(int index){
        return books.get(index);
    }

    public int getBookSize(){
        return books.size();
    }

    public void appendBook(Book book){
        books.add(book);
    }
}
