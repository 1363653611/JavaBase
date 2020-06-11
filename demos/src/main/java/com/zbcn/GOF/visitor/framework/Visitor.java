package com.zbcn.GOF.visitor.framework;

import com.zbcn.GOF.visitor.concrete.Directory;
import com.zbcn.GOF.visitor.concrete.File;

/**
 *  @title Visitor
 *  @Description 表示访问者的抽象类
 *  @author zbcn8
 *  @Date 2020/6/11 8:58
 */
public abstract class Visitor {
    public abstract void visit(File file);
    public abstract void visit(Directory directory);
}
