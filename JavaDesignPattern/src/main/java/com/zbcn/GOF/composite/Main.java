package com.zbcn.GOF.composite;

import com.zbcn.GOF.composite.concrete.Directory;
import com.zbcn.GOF.composite.concrete.File;

public class Main {

    public static void main(String[] args) {
        System.out.println("make root entries .....");
        Directory root = new Directory("root");
        Directory bin = new Directory("bin");
        Directory tmp = new Directory("tmp");
        Directory usr = new Directory("usr");
        root.add(bin);
        root.add(tmp);
        root.add(usr);
        bin.add(new File("vi.sh", 1000));
        bin.add(new File("latex.sh", 2000));
        root.printList();
    }
}
