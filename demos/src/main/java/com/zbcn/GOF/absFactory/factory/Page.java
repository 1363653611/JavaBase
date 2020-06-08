package com.zbcn.GOF.absFactory.factory;

import com.google.common.collect.Lists;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 *  @title Page
 *  @Description 抽象部件： 表示html 页面的类
 *  @author zbcn8
 *  @Date 2020/6/8 9:34
 */
public abstract class Page {

    private String title;

    private String author;

    private List<Item> content = Lists.newArrayList();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Page(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public  void add(Item item){
        content.add(item);
    }

    public List<Item> getContent() {
        return content;
    }

    public void setContent(List<Item> content) {
        this.content = content;
    }

    public void outPut(){
        String fileName = title + ".html";
        try {
            Writer writer = new FileWriter(fileName);
            writer.write(this.makeHTML());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract String makeHTML();
}
