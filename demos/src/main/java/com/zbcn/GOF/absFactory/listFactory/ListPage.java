package com.zbcn.GOF.absFactory.listFactory;

import com.zbcn.GOF.absFactory.factory.Item;
import com.zbcn.GOF.absFactory.factory.Page;

import java.util.Iterator;
import java.util.List;

public class ListPage extends Page {
    public ListPage(String title, String author) {
        super(title, author);
    }

    @Override
    public String makeHTML() {
        StringBuilder builder = new StringBuilder();
        builder.append("<html><head><title>"+ getTitle() + "<\\head><\\title>");
        builder.append("body\n>");
        builder.append("<h1>" + getTitle() + "</h1> \n");
        builder.append("<ul>\n");
        List<Item> content = getContent();
        Iterator<Item> iterator = content.iterator();
        while (iterator.hasNext()) {
            Item next = iterator.next();
            builder.append(next.makeHTML());
        }
        builder.append("</ul>\n");
        builder.append("<hr><address>" + getAuthor() +"</address>" );
        builder.append("</body></html>\n");
        builder.toString();

        return builder.toString();
    }
}
