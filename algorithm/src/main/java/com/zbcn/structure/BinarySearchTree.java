package com.zbcn.structure;

import java.util.Objects;

/**
 * 二叉查找树,自己写的代码,需要测试
 * @param <E>
 */
public class BinarySearchTree<E extends Comparable<E>> {

    private TreeNode<E> root = null;

    public TreeNode<E> getRoot() {
        return root;
    }

    public void setRoot(TreeNode<E> root) {
        this.root = root;
    }

    public BinarySearchTree(TreeNode<E> root) {
        this.root = root;
    }

    public BinarySearchTree() {
    }

    /**
     * 中序遍历
     * @param cursor
     */
    public void inOrder(TreeNode<E> cursor){
        if (Objects.isNull(cursor)){
            return;
        }
        inOrder(cursor.getLeft());
        System.out.println(cursor.getData());
        inOrder(cursor.getRight());
    }

    /**
     * 前序遍历
     * @param cursor
     */
    public void preOrder(TreeNode<E> cursor){
        if (Objects.isNull(cursor)){
            return;
        }
        System.out.println(cursor.getData());
        inOrder(cursor.getLeft());
        inOrder(cursor.getRight());
    }
    /**
     * 后续遍历
     * @param cursor
     */
    public void postOrder(TreeNode<E> cursor){
        if (Objects.isNull(cursor)){
            return;
        }
        inOrder(cursor.getLeft());
        inOrder(cursor.getRight());
        System.out.println(cursor.getData());
    }

    public TreeNode<E> search(E data) {
        return search(root, data);
    }

    /**
     * 非递归方式查找
     * @param currentNode
     * @param data
     * @return
     */
    public TreeNode<E> search(TreeNode<E> currentNode,E data){
        while (true){
            if (Objects.isNull(currentNode)){
                return null;
            }
            if(currentNode.getData().compareTo(data) == 0){
                return currentNode;
            }else if(currentNode.getData().compareTo(data) < 0){
                currentNode = currentNode.getRight();
            }else if(currentNode.getData().compareTo(data) > 0){
                currentNode = currentNode.getLeft();
            }
        }
    }

    /**
     * 查找的递归版本
     * @param currentNode
     * @param data
     * @return
     */
    public TreeNode<E> search2(TreeNode<E> currentNode,E data){
        if (Objects.isNull(currentNode)){
            return null;
        }
        if(currentNode.getData().compareTo(data) < 0){
           return search2(currentNode.getRight(),data);
        }else if(currentNode.getData().compareTo(data) > 0){
            return search2(currentNode.getLeft(),data);
        }else{
            //currentNode.getData().compareTo(data) == 0
            return currentNode;
        }
    }

    public void insert(E key){
        if(key != null){
            insert(new TreeNode<>(key));
        }
    }

    /**
     * 向节点插入数据
     * @param node
     */
    public void insert(TreeNode<E> node){
        TreeNode<E> currentNode = root;
        if(currentNode == null) {
            root = node;
            return;
        }else{
            while (true){
                if(node.getData().compareTo(currentNode.getData()) < 0){
                    if(currentNode.getLeft() == null){
                        break;
                    }else{
                        currentNode = currentNode.getLeft();
                    }
                }else if(node.getData().compareTo(currentNode.getData()) > 0){
                    if(currentNode.getRight() == null){
                        break;
                    }else{
                        currentNode = currentNode.getRight();
                    }
                }
            }
        }
        if(node.getData().compareTo(currentNode.getData()) < 0){
            currentNode.setLeft(node);
        }else if(node.getData().compareTo(currentNode.getData()) > 0){
            currentNode.setRight(node);
        }
        node.setParent(currentNode);
    }


    /**
     * 插入数据方式2
     * @param node
     */
    public void insert2(TreeNode<E> node){
        //比较大小标志
        int cmp = 0;
        //资源节点
        TreeNode<E> source = root;
        TreeNode<E> target = null;
        while (source!= null){
            target = source;
            cmp = node.getData().compareTo(source.getData());
            if(cmp > 0){
                source = source.getRight();
            }else{
                source = source.getLeft();
            }
        }
        node.setParent(target);
        if(target == null){ //父节点为null,说明当前节点为根节点
            root = node;
        }else {
            cmp = node.getData().compareTo(target.getData());
            if(cmp<0){
                target.setLeft(node);
            }else{
                target.setRight(node);
            }
        }

    }

    public TreeNode<E> getParent(TreeNode<E> currentNode,E data){
        if (Objects.isNull(currentNode)){
            return null;
        }
        TreeNode<E> target = currentNode;
        TreeNode<E> parent = null;
        while (target != null){
            //如果根节点为当前节点,则父节点为空
            if(target.getData().compareTo(data) == 0){
                return parent;
            }else if(target.getData().compareTo(data) < 0){
                parent = target;
                target = target.getRight();
            }else if(target.getData().compareTo(data) > 0){
                parent = target;
                target = target.getLeft();
            }

        }

        return null;
    }

    public void delete(E key){
        TreeNode<E> target = search2(root,key);
        if(target != null){
            delete(root,target);
        }
    }

    /**
     * 删除节点
     * @param rootNode
     * @param node
     */
    public void delete(TreeNode<E> rootNode,TreeNode<E> node){
        TreeNode<E> currentNode = Objects.isNull(rootNode) ? root:rootNode;
        //当前节点的父节点
        TreeNode<E> parentNode = getParent(currentNode, node.getData());
        int cmp = 0;
        //如果子节点都为空,则直接删除
        if(node.getLeft()== null && node.getRight()== null){
            cmp = parentNode.getData().compareTo(node.getData());
            if(cmp<0){//右侧节点
                parentNode.setRight(null);
            }else{
                parentNode.setLeft(null);
            }
            return;
        }
        //其中有一个节点不为空
        if(node.getLeft()!= null && node.getRight()!= null){
            //获取前驱节点(小于当前节点的最大节点)
            TreeNode<E> predecessor = predecessor(node);
            TreeNode<E> successor = successor(node);
            TreeNode<E>  target=  predecessor == null ? successor :predecessor;
            //如果删除的元素有两个儿子，那么可以取左子树中最大元素或者右子树中最小元素进行替换，然后将最大元素最小元素原位置置空
            //将当前节点和前驱节点值互换
            node.setData(target.getData());
            //todo 删除前驱节点
            TreeNode<E> parent = target.getParent();
            cmp = parent.getData().compareTo(target.getData());
            if(cmp < 0){
                parent.setRight(null);
            }else{
                parent.setLeft(null);
            }
            return;
        }

        //如果只有一个子节点
        E data;
        if(node.getLeft()== null){//是右侧节点
            data = node.getRight().getData();
            node.setRight(null);
        }else{
            data = node.getLeft().getData();
            node.setRight(null);
        }
        node.setData(data);
    }

    /**
     * 获取左侧的最大节点
     * @param node
     * @return
     */
    public TreeNode<E> getMaxNode(TreeNode<E> node){
        if(node == null){
            return null;
        }
        while(node.right != null){
            node = node.right;
        }
        return node;
    }

    public TreeNode<E> getMinNode(TreeNode<E> node){
        if(node == null){
            return null;
        }
        while(node.left != null){
            node = node.left;
        }
        return node;
    }

    /**
     * 前驱节点
     * 找结点(x)的前驱结点。即，查找"二叉树中数据值小于该结点"的"最大结点"。
     * @param node
     * @return
     */
    public TreeNode<E> predecessor(TreeNode<E> node){
        //若该节点有左子节点,则前驱节点为"以其左孩子为根的子树的最大结点"。
        if(node.getLeft() != null){
            return getMaxNode(node.getLeft());
        }

        //若该节点不存在左子节点:
        //1. 该节点为其父节点的右子节点,则"node的前驱结点"为 "它的父结点"
        //2. 该节点为其父节点的左子节点,则查找"node的最低的父结点，并且该父结点要具有右孩子"，找到的这个"最低的父结点"就是"x的前驱结点"
        //寻找其父辈节点中,左子树为null 的第一个父辈节点
        TreeNode<E> parent = getParent(root, node.data);
        // //node != parent.getLeft() 时,说明 node 就是 parent 的右子节点
        while (parent!= null && node == parent.getLeft()){
            node = parent;
            parent = getParent(root,parent.getData());
        }
        return parent;
    }

    /**
     * 后继节点
     * 找结点(x)的后继结点。即，查找"二叉树中数据值大于该结点"的"最小结点"。
     * @param node
     * @return
     */
    public TreeNode<E> successor(TreeNode<E> node){
        //1. 如果该节点有右侧节点,则后继结点"为 "以其右孩子为根的子树的最小结点"。
        if(node.getRight() != null){
            return getMinNode(node.getRight());
        }
        //如果改节点不存啊在右侧节点
        //1. 该节点为其父节点的左侧节点,则"node的后继结点"为 "它的父结点"
        //2. 该节点为其父节点的右侧节点,则查找"node的最低的父结点，并且该父结点要具有左孩子",找到的这个"最低的父结点"就是"x的后继结点"。
        TreeNode<E> parent = getParent(root, node.getData());
        //node != parent.getRight() 时,说明 node 就是 parent 的左子节点
        while (parent!=null && node == parent.getRight()){
            node = parent;
            parent = getParent(root,parent.getData());
        }
        return parent;
    }

    /**
     * 插入节点
     * @param tree
     * @param node
     */
    private void insert(TreeNode<E> tree,TreeNode<E> node){
        int cmp = 0;
        TreeNode<E> source = tree;
        //需要插入的目标位置
        TreeNode<E> target = null;
        //查找插入位置(可以插入当前节点的父节点)
        while(source != null){
            target = source;
            cmp = node.getData().compareTo(source.getData());
            if(cmp < 0){
                source = source.getLeft();
            }else {
                source = source.getRight();
            }
        }
        if(target == null){
            root = node;
        }else{
            if(cmp < 0){
                target.setLeft(node);
            }else{
                target.setRight(node);
            }
        }
    }


    /**
     * 删除指定节点
     * @param node
     * @return
     */
    public TreeNode<E> remove(TreeNode<E> node){
        // node 节点的左右子树都不为null
        if(node.getRight() != null && node.getLeft() != null){
            //找到后继节点
            TreeNode<E> successor = successor(node);
            //转移后继结点值到当前节点
            node.setData(successor.getData());
            //把要删除的当前节点设置为后继结点
            node = successor;
        }
        //只有一个子节点,或者没有子节点
        TreeNode<E> child = null;
        if(node.getLeft() != null){
            child = node.getLeft();
        }else{
            child = node.getRight();
        }
        //如果 child != null，就说明是有一个节点的情况
        if(child != null){
            //将子节点和父节点关联上
            child.setParent(node.getParent());
        }
        //如果当前节点没有父节点（后继情况到这儿时一定有父节点）
        //说明要删除的就是根节点
        TreeNode<E> parent = node.getParent();
        if( parent == null){
            //根节点设置为子节点
            //按照前面逻辑，根只有一个或者没有节点，所以直接赋值 child 即可
            root = child;
        }else if(node == parent.getLeft()){ //子节点时父亲的做左节点
            //将父节点的左节点设置为 child
            parent.setLeft(child);
        }else{//子节点时父亲的右子节点
            parent.setRight(child);
        }
        return node;
    }


    /**
     * 删除 key 指定的节点
     * @param key
     */
    public void remove(E key) {
        //获取要删除的节点
        TreeNode<E> node = search(root, key);
        //如果存在就删除
        if (node != null)
            remove(node);
    }


    public void destroy(TreeNode<E> tree){
        if (tree==null)
            return ;

        if (tree.left != null){
            destroy(tree.left);
        }
        if (tree.right != null){
            destroy(tree.right);
        }
        tree=null;
    }

    public void clear() {
        destroy(root);
        root = null;
    }

    /*
     * 打印"二叉查找树"
     *
     * key        -- 节点的键值
     * direction  --  0，表示该节点是根节点;
     *               -1，表示该节点是它的父结点的左孩子;
     *                1，表示该节点是它的父结点的右孩子。
     */
    public void print(TreeNode<E> tree,E key, int direction){
        if(tree != null){
            if(direction==0) {    // tree是根节点
                System.out.printf("%2d is root\n", tree.getData());
            } else {                // tree是分支节点
                System.out.printf("%2d is %2d's %6s child\n", tree.getData(), key, direction == 1 ? "right" : "left");
            }

            print(tree.getLeft(), tree.getData(), -1);
            print(tree.getRight(),tree.getData(),  1);
        }

    }
    public void print() {
        if (root != null) {
            print(root, root.getData(), 0);
        }
    }

    /*************************************** 格式化爱爱医你树的代码*****************************************************/
    public static final String  PREFIX_BRANCH = "├";//树枝
    public static final String  PREFIX_TRUNK  = "│ ";//树干
    public static final String  PREFIX_LEAF   = "└";//叶子
    public static final String  PREFIX_EMP    = "  ";//空
    public static final String  PREFIX_LEFT   = "─L─";//左
    public static final String  PREFIX_RIGTH  = "─R─";//右


    /**
     * 以树结构的形式,美化打印
     * @param tree
     */
    public static void formatPrint(BinarySearchTree tree){
        if(tree != null && tree.getRoot() != null){
            System.out.println(tree.getRoot().getData());
            formatPrint(tree.getRoot(), "");
        }
    }

    public static void formatPrint(BinarySearchTree.TreeNode node, String prefix){
        if(prefix == null){
            prefix = "";
        } else {
            prefix = prefix.replace(PREFIX_BRANCH, PREFIX_TRUNK);
            prefix = prefix.replace(PREFIX_LEAF, PREFIX_EMP);
        }
        if(hasChild(node)){
            if(node.getRight() != null){
                System.out.println(prefix + PREFIX_BRANCH + PREFIX_RIGTH + node.getRight().getData());
                if(hasChild(node.getRight())){
                    formatPrint(node.getRight(), prefix + PREFIX_BRANCH);
                }
            } else {
                System.out.println(prefix + PREFIX_BRANCH + PREFIX_RIGTH);
            }

            if(node.getLeft() != null){
                System.out.println(prefix + PREFIX_LEAF + PREFIX_LEFT + node.getLeft().getData());
                if(hasChild(node.getLeft())){
                    formatPrint(node.getLeft(), prefix + PREFIX_LEAF);
                }
            } else {
                System.out.println(prefix + PREFIX_LEAF + PREFIX_LEFT);
            }
        }
    }

    private static boolean hasChild(BinarySearchTree.TreeNode node){
        return node.getLeft() != null || node.getRight() != null;
    }

    /**
     * 树节点
     * @param <E>
     */
    public static class TreeNode<E extends Comparable<E>>{

        private E data;
        private TreeNode<E> left;
        private TreeNode<E> right;
        private TreeNode<E> parent;
        TreeNode(E theData){
            data = theData;
            left = null;
            right = null;
            parent = null;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public TreeNode<E> getLeft() {
            return left;
        }

        public void setLeft(TreeNode<E> left) {
            this.left = left;
        }

        public TreeNode<E> getRight() {
            return right;
        }

        public void setRight(TreeNode<E> right) {
            this.right = right;
        }

        public TreeNode<E> getParent() {
            return parent;
        }

        public void setParent(TreeNode<E> parent) {
            this.parent = parent;
        }
    }


}
