package com.zbcn.algorithm;

import lombok.Data;

/**
 * 链表功能测试
 *
 * @author likun
 * @since 2022/9/9 16:06
 */
public class T20_LinkedTest {

    @Data
    static class LinkNode<T> {

        /**
         * 当前节点的值
         */
        private T data;

        /**
         * 下一个节点
         */
        private LinkNode<T> next;

    }

    //单链表反转
    //链表中环的检测
    //两个有序的链表合并
    //删除链表倒数第 n 个结点
    //求链表的中间结点

    /**
     * 单链表反转: 给定一个链表，判断链表中是否有环。
     *
     * @param head
     * @param <T>
     * @return
     */
    public static <T> LinkNode<T> singleLinkReversal(LinkNode<T> head) {

        // 目标链表
        LinkNode des = null;
        // 当前链表
        LinkNode curr = head;

        while (curr != null) {
            // 将链表 第一个节点以后的子链表暂存临时对象
            LinkNode temp = curr.next;
            // 将之前的 des 的子链表链接到当前节点
            curr.next = des;
            // 新的链表付给目标对象
            des = curr;
            //  将临时对象付给当前
            curr = temp;
        }
        return des;
    }

    /**
     * 链表中环的检测
     * @param head
     * @param <T>
     */
    public static <T> void checkRing(LinkNode<T> head) {

    }


}
