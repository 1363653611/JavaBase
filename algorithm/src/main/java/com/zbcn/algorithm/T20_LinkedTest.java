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
     * 解题思路： 用快慢指针，一个走的快，一个走的慢，快指针最终会追上慢指针
     * @param head
     * @param <T>
     */
    public static <T> boolean circleCheck(LinkNode<T> head) {
        if(head == null){
            System.out.println("空链表，不存在回环");
            return false;
        }
        // 快慢指针都从相同的位置开始
        LinkNode fast = head;
        LinkNode slow = head;
        while (fast.next.next != null && slow.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                System.out.println("存在回环");
                return true;
            }
        }
        System.out.println("不存在回环");
        return false;
    }

    //两个有序的链表合并

    /**
     * 两个有序链表合并
     * @param node1
     * @param node2
     * @return
     */
    public LinkNode mergeTwoLinkNodes(LinkNode<Integer> node1, LinkNode<Integer> node2){
        if (node1 == null && node2 == null){
            return  null;
        }

        // 定义一个新的链表，用来标识链表的头的位置
        LinkNode<Integer> nodeH = new LinkNode<>();
        // 用来移动操作链表，初始位置，指向链表的头
        LinkNode<Integer> nodeM = node1;
        while (node1 != null && node2 != null){
            if (node1.getData() < node2.getData()){
                // 如果 node1 < node2 , 则 nodeM.next = node1
                nodeM.next = node1;
                node1 = node1.next;
            }else {
                nodeM.next = node2;
                // 移动 node2. 到 node2.next
                node2 = node2.next;
                // 移动 nodeM 到 nodeM.next
                nodeM = nodeM.next;
            }
        }
        if (node1 == null){
            nodeM.next = node2;
        }else {
            nodeM.next = node1;
        }

        return nodeH.next;
    }

    ///删除链表倒数第 n 个结点

    /**
     * 删除链表的末尾 第 tailPlace 个节点  (双指针)
     * @param head
     * @param tailPlace
     * @return
     */
    public LinkNode removeNthFromEnd(LinkNode head, Integer tailPlace){
        LinkNode fast = head;
        LinkNode slow = head;

        // 设置虚拟节点，保存 head 节点
        LinkNode<Integer> virtualNode = new LinkNode();
        virtualNode.data = -1;
        virtualNode.next = head;

        // 移动fast 节点，将其移动 tailPlace 位置
        while (tailPlace -1 > 0){
            fast = fast.next;
            tailPlace --;
        }

        // fast 为尾节点时，slow 指向了要删除的目标位置， 因为 fast  已经向后移动了 tailPlace 个位置

        LinkNode pre = virtualNode;

        while (fast.next != null){
            pre = slow;
            slow = slow.next;
            fast = fast.next;
        }
        pre.next = slow.next;

        return  virtualNode.getNext();
    }


    // 求链表的中间结点

    /**
     * 求链表的中间节点  （快慢指针法）
     * @param head
     * @return
     */
    public LinkNode findMidNode(LinkNode head){

        LinkNode fast = head;
        LinkNode slow = head;
        // fast 一定要在前，避免空指针
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }



}
