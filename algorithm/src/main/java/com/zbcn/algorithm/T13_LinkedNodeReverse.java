package com.zbcn.algorithm;

/**
 *  链表反转
 *  <br/>
 *  @author zbcn8
 *  @since  2021/5/4 15:49
 */
public class T13_LinkedNodeReverse {

    public static void main(String[] args) {
        ListNode head = new ListNode();
        head.value = 0;
        ListNode curr = head;
        for (int i = 0; i <= 5; i++){
            curr.next = new ListNode();
            curr.next.value = i + 1;
            curr = curr.next;
        }
       // ListNode listNode = reverseByLoop(head);
        ListNode listNode = recursion(head);
        System.out.println(listNode);
    }

    /**
     * 循环的方式反转链表
     * @param head
     * @return
     */
    public static ListNode reverseByLoop(ListNode head){
        ListNode pre= null;
        ListNode next;
        ListNode curr = head;
        while (curr != null){
            next = curr.next;
            curr.next = pre;
            //转换完成，将前一个元素指向为当前元素
            pre = curr;
            //下一个元素
            curr = next;
        }
        return pre;

    }

    /**
     * 递归方式
     * @param head
     * @return
     */
    public static ListNode recursion(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        //找到尾部节点
        ListNode newHead = recursion(head.next);
        //元素指针分开
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    static class ListNode {
        int value;
        ListNode next;
    }
}
