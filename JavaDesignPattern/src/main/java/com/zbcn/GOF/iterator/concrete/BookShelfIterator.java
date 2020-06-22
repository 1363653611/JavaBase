package com.zbcn.GOF.iterator.concrete;

import com.zbcn.GOF.iterator.entity.Book;
import com.zbcn.GOF.iterator.interfaces.Iterator;

public class BookShelfIterator implements Iterator {

    private BookShelf bookShelf;
    private int index;

    public BookShelfIterator(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        int bookSize = bookShelf.getBookSize();
        if(this.index < bookSize) {
            return true;
        }
        return false;
    }

    @Override
    public Object next() {
        Book bootAt = bookShelf.getBootAt(index);
        index ++;
        return bootAt;
    }
}
