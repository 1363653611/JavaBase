package com.zbcn.structure.test;

import com.zbcn.structure.BinarySearchTree;

public class BSTreeTest {

    public static void main(String[] args) {

        BinarySearchTree<Integer> bTree = createBTree();
        bTree.print();
        BinarySearchTree.TreeNode<Integer> parent = bTree.getParent(bTree.getRoot(), 30);
        System.out.println("指定父节点为:" + parent.getData());
        //bTree.remove(30);
//        bTree.print();
        bTree.formatPrint(bTree);
        bTree.delete(30);
        System.out.println("删除后........");
        bTree.formatPrint(bTree);
    }

   private static BinarySearchTree<Integer> createBTree(){
        int[] array = new int[]{50, 30, 80, 20, 35, 34, 32, 40, 70, 75, 100};
       BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int i = 0; i < array.length; i++) {
            tree.insert(array[i]);
        }
        return tree;
    }



}
