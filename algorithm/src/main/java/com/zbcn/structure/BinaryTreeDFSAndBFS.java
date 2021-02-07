package com.zbcn.structure;

import lombok.Data;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 深度搜索和广度搜索
 * <br/>
 *
 * @author zbcn8
 * @since 2021/1/28 14:00
 */
public class BinaryTreeDFSAndBFS<T> {

    TreeNode<T> root;

    /**
     * 采用递归的方式创建一颗二叉树
     * 1. 传入的是二叉树的数组表示法
     * 2. 构造后是二叉树的二叉链表表示法
     *
     * @param arr
     * @param index
     * @return
     */
    public <T> TreeNode makeBinaryTreeByArray(T[] arr, int index) {
        if (index < arr.length) {
            T value = arr[index];
            if (value != null) {
                TreeNode<T> node = new TreeNode<T>(value);
                arr[index] = null;
                node.left = makeBinaryTreeByArray(arr, index * 2);
                node.right = makeBinaryTreeByArray(arr, index * 2 + 1);
                return node;
            }
        }
        return null;
    }


    public <T> BinaryTreeDFSAndBFS(T[] array) {
        this.root = makeBinaryTreeByArray(array, 1);
    }

    /**
     * DFS 深度优先遍历，相当于先根遍历
     * 采用非递归实现
     * 需要辅助数据结构：栈
     */
    public void depthOrderTraversal(TreeNode<T> tree) {
        if (tree == null) {
            System.out.println("tree is empty!");
            return;
        }
        Deque<TreeNode<T>> stack = new ArrayDeque<>();
        stack.push(tree);
        while (!stack.isEmpty()){
            TreeNode<T> value = stack.pop();
            System.out.println(value.data);
            //先右边，后左边
            if(value.right != null){
                stack.push(value.right);
            }
            if(value.left != null){
                stack.push(value.left);
            }
        }
        System.out.println("\\n");
    }

    /**
     * 广度优先遍历
     * 采用非递归实现
     * 需要辅助数据结构：队列
     * @param tree
     */
    public void levelOrderTraversal(TreeNode<T> tree){
        if(tree == null){
            System.out.println("empty tree");
            return;
        }
        //队列
        Deque<TreeNode<T>> queue = new ArrayDeque<>();
        queue.add(tree);
        while (!queue.isEmpty()){
            TreeNode<T> node = queue.remove();
            System.out.println(node.data + "");
            if(node.left != null){
                queue.add(node.left);
            }
            if(node.right != null){
                queue.add(node.right);
            }
        }
        System.out.println("\\n");

    }

    @Data
    static class TreeNode<T> {
        private T data;
        private TreeNode<T> left;
        private TreeNode<T> right;

        public TreeNode(T data) {
            this.data = data;
        }
    }

    /**
     * 13
     * / \
     * 65 5
     * / \ \
     * 97 25 37
     * / /\ /
     * 22 4 28 32
     */
    public static void main(String[] args) {
        Integer[] arr={0,13,65,5,97,25,0,37,22,0,4,28,0,0,32,0};
        BinaryTreeDFSAndBFS<Integer> tree= new BinaryTreeDFSAndBFS<>(arr);
        //tree.depthOrderTraversal(tree.root);
        tree.levelOrderTraversal(tree.root);
    }
}
