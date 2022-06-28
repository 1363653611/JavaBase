package com.zbcn.algorithm;

import lombok.Data;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ac 自动机
 *
 * @author likun
 * @since 2022/5/29 10:45
 */
public class AhoCorasick {

    private AcNode root = new AcNode('/'); // 存储无意义字符


    public void match(char[] text){ // text 是主串
        int n = text.length;
        AcNode p = root;
        for (int i = 0; i < n; ++i){
            int idx = text[i] - 'a';
            while (p.children[idx] == null && p != root){
                // 失败指针发挥作用的地方
                p = p.fail;
            }
            p = p.children[idx];
            // 如果没有匹配的，从 root 开始重新匹配
            if (p == null){
                p = root;
            }
            AcNode temp = p;
            while (temp != root){ // 打印出可以匹配的模式串
                if (temp.isEndingChar == true){
                    int pos = i -temp.length;
                    System.out.println("匹配的起始下标 " + pos + "; 长度" + temp.length);
                }
                temp = temp.fail;
            }
        }

    }


    /**
     * 构建失败指针
     */
    public void buildFailurePointer(){

        Queue<AcNode> queue= new LinkedList<>();
        root.fail = null;
        queue.add(root);
        while (!queue.isEmpty()){
            AcNode p = queue.remove();
            for (int i= 0; i <26; ++i){
                AcNode pc = p.getChildren()[i];
                if (pc == null){
                    continue;
                }
                if (pc == root){
                    pc.fail = root;
                } else {
                    AcNode q = p.getFail();
                    while (q != null){
                        AcNode qc = q.getChildren()[pc.data - 'a'];
                        if (qc != null){
                            pc.fail = qc;
                            break;
                        }
                        q = q.fail;
                    }


                    if (q == null){
                        pc.fail = root;
                        break;
                    }
                }
                queue.add(pc);
            }
        }

    }




    /**
     * AC自动机构建
     * 1. 将多个模式串构建成 Trie 树；
     * 2. 在 Trie 树上构建失败指针（相当于 KMP 中的失效函数 next 数组）。
     *
     */
    @Data
    public static class AcNode{
        public char data;

        /**
         * 字符集 只包含 a-z 这 26 个字符
         */
        private AcNode[] children = new AcNode[26];

        /**
         * 是否是结尾字符： 结尾字符为true
         */
        private boolean isEndingChar = false;

        /**
         *  当 isEndingChar = true 时，记录字符串长度
         */
        private int  length = -1;

        /**
         * 是被指针
         */
        private AcNode fail;

        public AcNode(char data) {
            this.data = data;
        }
    }
}
