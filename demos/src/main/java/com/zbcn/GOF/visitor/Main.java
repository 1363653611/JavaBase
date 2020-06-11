package com.zbcn.GOF.visitor;

import com.zbcn.GOF.visitor.concrete.Directory;
import com.zbcn.GOF.visitor.concrete.File;
import com.zbcn.GOF.visitor.concrete.ListVisitor;

public class Main {
    public static void main(String[] args) {

        System.out.println("make root entries.....");
        Directory root = new Directory("root");
        Directory bin = new Directory("bin");
        Directory tmp = new Directory("tmp");
        Directory usr = new Directory("usr");
        root.add(bin);
        root.add(tmp);
        root.add(usr);
        bin.add(new File("vi.sh", 1000));
        bin.add(new File("latest.sh", 2000));
        root.accept(new ListVisitor());
        System.out.println("");
    }
}
