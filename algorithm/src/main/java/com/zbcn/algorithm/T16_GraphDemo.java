package com.zbcn.algorithm;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 图 算法
 *
 * @author likun
 * @since 2022/6/28 20:05
 */
public class T16_GraphDemo {


    /**
     * 无向图
     */
    public static class Graph {

        /**
         * 顶点的个数
         */
        private int v;

        /**
         * 邻接表
         */
        private LinkedList<Integer> adj[];

        public Graph(int v) {
            this.v = v;
            // 构建空的临 接表
            adj = new LinkedList[v];
            for (int i = 0; i < v; i++) {
                adj[i] = new LinkedList<>();
            }
        }

        // 无向图一条边存两次
        public void addEdge(int s, int t) {
            adj[s].add(t);
            adj[t].add(s);
        }

        /**
         * 广度优先搜索 breadth first search
         * 通过 s 找到 顶点 t
         *
         * @param s 顶点 s
         * @param t 顶点 t
         * @return
         */
        public void bfs(int s, int t) {
            if (s == t) {
                return;
            }
            // 用来记录已经被访问的顶点
            boolean[] visited = new boolean[v];
            visited[s] = true;
            // 用来存储已经被访问、但相连的顶点还没有被访问的顶点
            Queue<Integer> queue = new LinkedList<>();
            queue.add(s);
            //prev用来记录搜索路径
            int[] prev = new int[v];
            for (int i = 0; i < v; i++) {
                prev[i] = -1;
            }
            while (queue.size() != 0) {
                Integer w = queue.poll();
                // 遍历顶点对应的列表
                for (int i = 0; i < adj[w].size(); ++i) {
                    int q = adj[w].get(i);
                    if (!visited[q]) {
                        prev[q] = w;
                    }
                    if (q == t) {
                        print(prev, s, t);
                    }
                    visited[q] = true;
                    queue.add(q);
                }

            }
        }

        private void print(int[] prev, int s, int t) { // 递归打印 s->t 的路径
            if (prev[t] != -1 && t != s) {
                print(prev, s, prev[t]);
            }
            System.out.print(t + " ");
        }


        /****************深度优先搜索 - DFS*******************/
        /**
         * 全局变量或者类成员变量
         */
        boolean found = false;

        /**
         * 深度优先搜索 depth first search  （不撞南墙不回头）
         * 通过 s 直到到顶点 t   （使用的算法思想： 回溯算法  -- 非常适合使用递归算法来实现）
         * @param s
         * @param t
         */
        public void dfs(int s,int t){
            found = false;
            boolean[] visited = new boolean[v];
            int[] prev = new int[v];
            for (int i = 0; i < v; ++i){
                prev[i] = -1;
            }
            
            recurDfs(s,t, visited,prev);
            print(prev, s,t);
        }

        private void recurDfs(int w, int t, boolean[] visited, int[] prev) {
            if (found == true){
                return;
            }
            visited[w] = true;
            if (w == t){
                found = true;
                return;
            }
            for (int i = 0; i < adj[w].size(); i++){
                int q = adj[w].get(i);
                if (!visited[q]){
                    prev[q] = w;
                    recurDfs(q,t,visited,prev);
                }
            }
        }


    }
}
