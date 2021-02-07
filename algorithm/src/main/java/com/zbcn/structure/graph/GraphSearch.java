package com.zbcn.structure.graph;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * 图的广度优先搜索和深度优先搜索实现
 * <br/>
 *
 * @author zbcn8
 * @since 2021/1/27 11:40
 */
public class GraphSearch<T> {

    private static StringBuffer searchPathDFS = new StringBuffer();

    private static StringBuffer searchPathBFS = new StringBuffer();


    /**
     * 深度有限搜索法
     * @param root
     */
    public void searchDFS(GraphNode<T> root){
        if (root == null){
            return;
        }
        if (searchPathDFS.length() > 0){
            searchPathDFS.append("->");
        }
        searchPathDFS.append(root.data.toString());
        root.visited = true;
        for (GraphNode<T> node :root.neighborList){
            if (!node.visited){
                searchDFS(node);
            }
        }
    }

    /**
     * 广度优先搜索实现,使用队列
     * @param root
     */
    public void searchBFS(GraphNode<T> root){
        Deque<GraphNode<T>> queue = new ArrayDeque<>();
        if(searchPathBFS.length() > 0){
            searchPathBFS.append("->");
        }
        searchPathBFS.append(root.data.toString());
        root.visited = true;
        // 添加到队尾
        queue.addLast(root);
        while (!queue.isEmpty()){
            GraphNode<T> r = queue.pollLast();
            for (GraphNode<T> node : r.neighborList) {
                if(!node.visited){
                    searchPathBFS.append("->");
                    searchPathBFS.append(node.data.toString());
                    node.visited = true;
                    queue.addLast(node);
                }
            }
        }
    }

    @Data
    static class GraphNode<T> {
        T data;
        List<GraphNode<T>> neighborList;
        boolean visited;

        public GraphNode(T data) {
            this.data = data;
            this.neighborList = Lists.newArrayList();
            this.visited = false;
        }

        public boolean equals(GraphNode<T> node) {
            return this.data.equals(node.data);
        }

        /**
         * 还原图中所有节点为未访问
         */
        public void restoreVisited() {
            restoreVisited(this);
        }

        /**
         * 还原node的图所有节点为未访问
         * @param node
         */
        private void restoreVisited(GraphNode<T> node) {
            if (node.visited) {
                node.visited = false;
            }
            List<GraphNode<T>> neighbors = node.neighborList;
            for (int i = 0; i < neighbors.size(); i++) {
                restoreVisited(neighbors.get(i));
            }
        }
    }

    static GraphNode<Integer> node1;
    static GraphNode<Integer> node2;
    static GraphNode<Integer> node3;
    static GraphNode<Integer> node4;
    static GraphNode<Integer> node5;
    static GraphNode<Integer> node6;
    static GraphNode<Integer> node7;
    static GraphNode<Integer> node8;
    static GraphNode<Integer> node9;
    static GraphNode<Integer> node10;

    public static void main(String[] args) {
        buildGraph();
//        GraphSearch<Integer> graphSearch = new GraphSearch<Integer>();
//        graphSearch.searchDFS(node1);
//        System.out.println(searchPathDFS.toString());

        GraphSearch<Integer> graphSearchBFS = new GraphSearch<Integer>();
        graphSearchBFS.searchBFS(node1);
        System.out.println(searchPathBFS.toString());
    }

    private static void buildGraph() {

        node1 = new GraphNode<Integer>(1);
        node2 = new GraphNode<Integer>(2);
        node3 = new GraphNode<Integer>(3);
        node4 = new GraphNode<Integer>(4);
        node5 = new GraphNode<Integer>(5);
        node6 = new GraphNode<Integer>(6);
        node7 = new GraphNode<Integer>(7);
        node8 = new GraphNode<Integer>(8);
        node9 = new GraphNode<Integer>(9);
        node10 = new GraphNode<Integer>(10);

        node1.neighborList.add(node2);
        node1.neighborList.add(node3);

        node2.neighborList.add(node4);
        node2.neighborList.add(node5);
        node2.neighborList.add(node6);

        node3.neighborList.add(node1);
        node3.neighborList.add(node6);
        node3.neighborList.add(node7);
        node3.neighborList.add(node8);

        node4.neighborList.add(node2);
        node4.neighborList.add(node5);

        node5.neighborList.add(node2);
        node5.neighborList.add(node4);
        node5.neighborList.add(node6);

        node6.neighborList.add(node2);
        node6.neighborList.add(node5);
        node6.neighborList.add(node3);
        node6.neighborList.add(node8);
        node6.neighborList.add(node9);
        node6.neighborList.add(node10);

        node7.neighborList.add(node3);

        node8.neighborList.add(node3);
        node8.neighborList.add(node6);
        node8.neighborList.add(node9);

        node9.neighborList.add(node6);
        node9.neighborList.add(node8);
        node9.neighborList.add(node10);

        node10.neighborList.add(node6);
        node10.neighborList.add(node9);
    }


}
