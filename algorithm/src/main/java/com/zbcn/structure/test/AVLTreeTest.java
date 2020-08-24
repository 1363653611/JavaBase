package com.zbcn.structure.test;

import com.zbcn.structure.AVLTree;
import com.zbcn.structure.BinarySearchTree;

/**
 *  平衡树测试
 *  <br/>
 *  @author zbcn8
 *  @since  2020/8/21 9:39
 */
public class AVLTreeTest {

    public static void main(String[] args) {
        testBSTree();
        System.out.println("----------------------------");
        testAVLTree();
    }

    private static void testBSTree(){
        BinarySearchTree<Integer> bsTree = new BinarySearchTree<>();
        bsTree.insert(11);
        bsTree.insert(22);
        bsTree.insert(33);
        bsTree.insert(44);
        bsTree.insert(55);
        bsTree.insert(66);
        bsTree.print();
    }

    private static void testAVLTree(){
        AVLTree<Integer> valTree = new AVLTree<>();
        valTree.put(11);
        valTree.put(22);
        valTree.put(33);
        valTree.put(44);
        valTree.put(55);
        valTree.put(66);
        valTree.levelIterator();
    }
}
