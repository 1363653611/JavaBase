package com.zbcn.algorithm;
import lombok.Data;

import java.util.Stack;

/**
 *  链表: 添加,删除节点
 *  <br/>
 *  @author zbcn8
 *  @since  2021/3/23 15:10
 */
public class LinkedDemo_03 {

    /**
     * 链表节点信息
     */
    @Data
    static class LinkNode {
        int val;
        LinkNode next;
        public LinkNode(int val) {
            this.val = val;
        }
    }

    /**
     * 添加节点
     * @param head
     * @param value
     * @return
     */
    public static LinkNode addNode(LinkNode head, int value){
        //创建新节点
        LinkNode newNode = new LinkNode(value);
        //如果头节点为空 则新节点赋值给头结点
        if(head == null){
            head = newNode;
        }else{
            //添加到节点的尾部
            while (head.next == null){
                head.next = newNode;
            }
        }
        return head;
    }

    /**
     * 移除给定值相同的节点
     * @param head
     * @param value
     * @return
     */
    public static LinkNode removeNode(LinkNode head, int value){
        //如果链表为空
        if(head == null){
            return null;
        }
        //如果头结点就是 需要移动的节点
        LinkNode preNode = head;
        LinkNode des = head.next;
        while (des != null){
            if(preNode.val == value){ //如果为头节点,则需要改动一下头节点的指向
                preNode = des;
                des = des.next;
                head = preNode;
            } else if (des.val == value){ //中间节点中找到了需要删除的内容
                des = des.next;
                preNode.next = des;
            }else{ //未找到节点
                preNode = des;
                des = des.next;
            }
        }
        return head;
    }

    private static LinkNode remove2(LinkNode head, int value){
        //如果头节点是需要删除的节点
        while(head != null){
            if(head.val != value){
                break;
            }
            head = head.next;
        }
        //如果为非头节点
        //前趋节点
        LinkNode preNode = head;
        LinkNode currNode = head.next;
        while (currNode != null){
            if (currNode.val == value){
                preNode.next = currNode.next;
            }else{
                preNode = currNode;
            }
            currNode = currNode.next;
        }
        return head;
    }

    //fixme: 空间复杂度太大了
    public static LinkNode removeNodeUseStack(LinkNode head, int value){
        //如果链表为空
        if(head == null){
            return null;
        }
        Stack<LinkNode> stack = new Stack<>();
        LinkNode cur = head;
        while (cur != null){
            if(cur.val != value){
                stack.push(cur);
            }
            cur = cur.next;
        }
        // 重新组装数据
        // 尾节点为 null, 第一次循环式, 上一次循环已经将 cur 置为 null了
        while (!stack.isEmpty()){
            stack.peek().next = cur;
            cur = stack.pop();
        }
        return cur;
    }

    /**
     * 反转链表打印
     * @param head
     */
    private static void reversePrint(LinkNode head){
        //反转链表 打印
        if (head == null){
            return;
        }
        //反转链表
        LinkNode des = null;
        LinkNode cur = head;
        while (cur != null){
            //cur 的后继节点,接下来需要遍历的节点
            LinkNode temp = cur.next;
            // 当前节点cur 的后继节点 指向 前一次遍历时存储的节点des(第一次时,des = null)
            cur.next = des;
            // 位置交换完毕,将 des 节点指向 当前节点 cur
            des = cur;
            // 让当前节点指向之前保存的临时节点(下一次遍历的起始位置)
            cur = temp;
        }
        //循环打印
        while (des != null){
            System.out.println("-->" + des.val);
            des = des.next;
        }
    }

    /**
     * 用栈的方式打印
     * @param head
     */
    private static void printUseStack(LinkNode head){
        Stack<Integer> stack = new Stack<>();
        //将数据压入栈中
        while (head != null){
            stack.push(head.val);
            head = head.next;
        }
        //循环打印栈中的数据
        while (!stack.isEmpty()) {
            System.out.println("---->" + stack.pop());
        }
    }

    /**
     * 使用递归的方式打印
     * @param head
     */
    private static void printUseRecursion(LinkNode head){
        if(head== null){
            System.out.println("空的链表....");
        }
        if (head.next != null){ //递归
            printUseRecursion(head.next);
        }
        System.out.println("---->" + head.val);
    }



    public static void main(String[] args) {
        LinkNode linkNode = new LinkNode(1);
        LinkNode preNode = linkNode;
        for (int i = 1; i< 10; i++){
            LinkNode current  = new LinkNode(i);
//            if(i >5){
//                current = new LinkNode(1);
//            }
            preNode.next = current;
            preNode = current;
        }

        //LinkNode result = removeNode(linkNode, 1);
        //LinkNode result = remove2(linkNode, 1);
        //LinkNode result = removeNodeUseStack(linkNode, 1);
        //reversePrint(linkNode);
        //printUseStack(linkNode);
        printUseRecursion(linkNode);
    }
}
