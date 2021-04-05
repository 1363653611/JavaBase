package com.zbcn.algorithm;

import java.util.Arrays;

/**
 * 重建二叉树
 */
public class T4_RebuildBTree {

    /**
     *有了前序遍历，首先我们可以知道根节点的值，也就是数组中下标为0的位置，由此创建根节点。
     * 然后在中序遍历中找到根的值所在的下标，切出左右子树的前序和中序。
     * @param preOrder
     * @param inOrder
     * @return
     */
    private BTreeNode buildTree(int[] preOrder, int[] inOrder){

        if(preOrder.length == 0 || inOrder.length == 0){
            return null;
        }
        //前序遍历的第一个节点就是根节点
        //根节点
        BTreeNode root = new BTreeNode();
        root.value = preOrder[0];
       //中序遍历的根节点位置
        int leftSize = find(inOrder, root.value);

        // 左子树
        int[] leftPreOrder = Arrays.copyOfRange(preOrder,1,leftSize+1);
        int[] leftInOrder = Arrays.copyOfRange(inOrder, 0, leftSize);
        root.left = buildTree(leftPreOrder,leftInOrder);
        //右子树
        int[] rightPreOrder = Arrays.copyOfRange(preOrder, leftSize+1, preOrder.length);
        int[] rightInOrder = Arrays.copyOfRange(inOrder,leftSize +1, inOrder.length);
        root.right = buildTree(rightPreOrder,rightInOrder);
        return root;
    }

    private int find(int[] inOrder, int value) {
        for (int i =0 ; 0 < inOrder.length ; i++){
            if(value == inOrder[i]){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] preOrders= new int[]{1, 2, 4, 7, 3, 5, 6, 8};
        int[] inOrder= new int[]{4,7,2,1,5,3,8,6};
        T4_RebuildBTree t4_rebuildBTree = new T4_RebuildBTree();
        BTreeNode bTreeNode = t4_rebuildBTree.buildTree(preOrders, inOrder);
        System.out.println(bTreeNode);
    }

    /**
     * 二叉树的节点
     */
    static class BTreeNode{
        int value;
        BTreeNode left;
        BTreeNode right;
    }



}
