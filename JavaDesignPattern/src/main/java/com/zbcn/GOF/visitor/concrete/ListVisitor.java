package com.zbcn.GOF.visitor.concrete;

import com.zbcn.GOF.visitor.framework.Entry;
import com.zbcn.GOF.visitor.framework.Visitor;

import java.util.Iterator;

/**
 *  @title ListVisitor
 *  @Description 具体的访问者类
 *  @author zbcn8
 *  @Date 2020/6/11 9:23
 */
public class ListVisitor extends Visitor {

    /**
     * 当前访问的文件夹名字
     */
    private String currentDir = "";

    @Override
    public void visit(File file) {
        System.out.println(currentDir + "/" + file);
    }

    @Override
    public void visit(Directory directory) {
        System.out.println(currentDir + "/" + directory);
        String saveDir = currentDir;
        currentDir = currentDir + "/" + directory.getName();
        Iterator iterator = directory.iterator();
        while (iterator.hasNext()){
            Entry next = (Entry)iterator.next();
            next.accept(this);
        }
        currentDir = saveDir;
    }
}
