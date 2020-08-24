package com.zbcn.structure;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * balanced Binary tree 平衡二叉树
 * <br/>
 *
 * @author zbcn8
 * @since 2020/8/19 13:31
 */
public class AVLTree<T extends Comparable<T>> {

    private Node<T> root;

    /**
     * @description: 返回两个数中的最大值
     */
    private int max(int a, int b) {
        return a > b ? a : b;
    }

    /**
     * @description: 获得当前结点的高度
     */
    private int height(Node x) {
        if (x == null) return 0;
        return x.getHeight();
    }

    /**
     * 获得平衡因子
     * @param x
     * @return 返回平衡因子：
     *  > 1 LL型 左子树高 --> 右旋解决
     *  < 1 RR型 右子树高 --> 左旋解决
     */
    private int getBalance(Node x) {
        if (x == null) return 0;
        return height(x.getLeft()) - height(x.getRight());
    }

    /**
     * 右旋
     * @param node
     * @return
     */
    public Node<T> rotateRight(Node<T> node){
        //获取左儿子
        Node<T> left = node.getLeft();
        //将左儿子的右儿子（"拖油瓶"结点）链接到旋转后的node的左链接中
        node.setLeft(left.getRight());
        // 调转node和它左儿子的父子关系，使node成为它原左儿子的右子树
        left.setRight(node);
        // 更新并维护受影响结点的height
        node.setHeight(max(height(node.getLeft()), height(node.getRight()))+1);
        // 更新并维护受影响结点的height
        left.setHeight(max(height(left.getLeft()),height(left.getRight()))+1);
        return left;
    }

    /**
     * 左旋
     * @param node
     * @return
     */
    public Node<T> rotateLeft(Node<T> node){
        //获取右儿子
        Node<T> right = node.getRight();
        //将右儿子的左儿子("拖油瓶"结点）链接到旋转后的node的右侧链接中
        node.setRight(right.getLeft());
        //调转node 节点和它右侧儿子的父子关系,使node 成为它原右生儿子的左子树
        right.setLeft(node);
        node.setHeight(max(height(node.getRight()),height(node.getLeft()))+1);
        right.setHeight(max(height(right.getLeft()),height(right.getRight()))+1);
        return right;
    }


    /**
     * 品衡化操作， 检查当前节点是否失衡，如果失衡则需要平衡化
     * @param node 当前节点
     * @return
     */
    public Node<T> reBalance(Node<T> node){
        // 获取平衡因子
        int balanceFactor = getBalance(node);
        // LL型，进行单次右旋 -> 在node的左子树根节点的左子树上插入节点而破坏平衡
        //getBalance(node.getLeft()) > 0 说明左侧子树的左侧孩子比右侧侧孩子高
        if(balanceFactor > 1 && getBalance(node.getLeft()) > 0){
            return rotateRight(node);
        }
        //LR型 先左旋再右旋   --->在node的左子树根节点的右子树上插入节点而破坏平衡
        // getBalance(node.getLeft())<=0 说明左侧子树的右侧孩子比左侧孩子高
        if(balanceFactor > 1&& getBalance(node.getLeft())<=0) {
            Node t = rotateLeft(node);
            return rotateRight(t);
        }
        //RR型， 进行单次左旋   在 A 的右子树根节点的右子树上插入节点而破坏平衡
        //getBalance(node.getRight())<=0 说明右子树在的右侧孩子比左侧孩子高
        if(balanceFactor < -1 && getBalance(node.getRight())<=0) {
            return rotateLeft(node);
        }
        // RL型，先右旋再左旋   在 A 的右子树根节点的左子树上插入节点而破坏平衡
        //getBalance(node.getRight())>0 说明右子树在的左侧孩子比右侧孩子高
        if(balanceFactor < -1 && getBalance(node.getRight())>0) {
            Node t = rotateRight(node);
            return rotateLeft(t);
        }
        return node;
    }

    public Node<T> put(T val){
        root = put(root,val);
        return root;
    }

    /**
     * 插入结点
     * @param node
     * @param val
     * @return
     */
    public Node<T> put(Node<T> node, T val){
        //如果节点为空，新建节点
        if(node == null){
            return new Node<>(val);
        }
        // 比较每个节点的大小
        int cmp = val.compareTo(node.getVal());
        //如果当前值小于node 的节点，则在左侧加入
        if( cmp < 0){
            node.setLeft(put(node.getLeft(), val));
        }else if(cmp > 0){
            //如果当前值大于node的节点，则在右侧加入
            node.setRight(put(node.getRight(), val));
        }else{ //cmp == 0 值相等，则替换
            node.setVal(val);
        }
        //沿递归路径从下至上更新结点height属性
        node.setHeight(max(height(node.getLeft()), height(node.getRight()))+1);
        // 沿递归路径从下往上, 检测当前结点是否失衡，若失衡则进行平衡化
       return reBalance(node);
    }

    /**
     * 返回节点中的最小节点
     * @param node
     * @return
     */
    private Node<T> min(Node<T> node){
        // 如果左儿子为空，则当前结点键为最小值，返回
        if(node.getLeft() == null){
            return null;
        }
        // 如果左儿子不为空，则继续向左递归
        return min(node);
    }

    /**
     * 获取最小值
     * @return
     */
    public T min () {
        if(root == null) {
            return null;
        }
        return min(root).getVal();
    }

    /**
     * 删除最小键的结点
     * @param node
     * @return
     */
    public Node<T> deleteMin(Node<T> node){
        // 如果当前结点左儿子空，则将右儿子返回给上一层递归的node.left
        if(node.getLeft() == null){
            return node.getRight();
        }
        // 向左子树递归， 同时重置搜索路径上每个父结点指向左儿子的链接
        return deleteMin(node.getLeft());
    }

    /**
     * 删除最小键的结点
     */
    public void deleteMin () {
        root = deleteMin(root);
    }

    /**
     * 删除给定val 的node
     * @param val
     * @param node
     * @return
     */
    public Node<T> delete(T val,Node<T> node){
        if(node == null){
            return null;
        }
        int cmp = val.compareTo(node.getVal());
        if(cmp < 0){// 向左子树查找键为key的结点
            node.setLeft(delete(val, node.getLeft()));
        }else if(cmp > 0){// 向右子树查找键为key的结点
            node.setRight(delete(val,node.getRight()));
        }else{
            // 结点已经被找到，就是当前的x
            // 如果左子树为空，则将右子树赋给父节点的链接
            if(node.getLeft() == null){
                return node.getRight();
            }
            // 如果右子树为空，则将左子树赋给父节点的链接
            if(node.getRight() == null){
                return node.getLeft();
            }
            Node<T> inherit = min(node.getRight()); // 取得结点node的继承结点
            // 将继承结点从原来位置删除，并重置继承结点右链接
            inherit.setRight(deleteMin(node.getRight()));
            // 重置继承结点左链接
            inherit.setLeft(node.getLeft());
        }
        if(root == null){
            return null;
        }
        // 沿递归路径从下至上更新结点height属性
        node.setHeight(max(height(node.getLeft()), height(node.getRight()))+1);
        // 沿递归路径从下往上, 检测当前结点是否失衡，若失衡则进行平衡化
        return reBalance(node);
    }

    /**
     * 删除节点
     * @param key
     */
    public void delete (T key) {
        root = delete(key, root);
    }

    public void levelIterator(){
        LinkedBlockingQueue<Node<T>> queue = new LinkedBlockingQueue<>();
        Node<T> current;
        int childSize = 0;
        int parentSize = 1;
        queue.offer(root);
        while (!queue.isEmpty()){
            current = queue.poll();
            System.out.print(current.val +" ");
            //如果当前节点的左节点不为空入队
            if(current.left != null){
                queue.offer(current.left);
                childSize ++;
            }
            //如果当前节点的右节点不为空，把右节点入队
            if(current.right != null){
                queue.offer(current.right);
                childSize++;
            }
            parentSize--;
            if(parentSize == 0){
                parentSize = childSize;
                childSize = 0;
                System.out.println("");
            }
        }
    }

    /**
     * 平衡二叉树的节点
     * <br/>
     *
     * @author zbcn8
     * @since 2020/8/19 13:32
     */
    public static class Node<T extends Comparable<T>> {

        /**
         * 节点值
         */
        private T val;

        /**
         * 深度，这里计算每个结点的深度，通过深度的比较可得出是否平衡
         */
        private int height;

        /**
         * 父节点
         */
        private Node<T> parent;

        /**
         * 左子树
         */
        private Node<T> left;

        /**
         * 右子树
         */
        private Node<T> right;

        public Node(T val) {
            this.val = val;
            height = 0;
            parent = null;
            left = null;
            right = null;

        }

        public T getVal() {
            return val;
        }

        public void setVal(T val) {
            this.val = val;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public Node<T> getParent() {
            return parent;
        }

        public void setParent(Node<T> parent) {
            this.parent = parent;
        }

        public Node<T> getLeft() {
            return left;
        }

        public void setLeft(Node<T> left) {
            this.left = left;
        }

        public Node<T> getRight() {
            return right;
        }

        public void setRight(Node<T> right) {
            this.right = right;
        }
    }
}
