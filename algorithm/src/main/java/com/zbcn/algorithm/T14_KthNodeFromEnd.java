package com.zbcn.algorithm;

/**
 * 链表中的 倒数第 k 个节点
 *
 * 输入一个链表，输出该链表的倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。例如链表有6个节点。从头节点开始他们的节点依次是 1,2,3,4,5,6。这个链表的倒数第三个节点时 4.
 *
 * @author likun
 * @since 2021/8/18 8:56
 */
public class T14_KthNodeFromEnd {

    public static void main(String[] args) {

        int len = 6;
        //创建链表
        ListNode head = createLinkNode(len);
        //倒数第 ？ 节点
        int reciprocal = 3;
        ListNode target = findKKth2Tail(head, reciprocal);

        ListNode target2 = findKKth2Tail2(head, reciprocal);
        ListNode target3 = findKKth2Tail3(head, reciprocal);
    }

    private static ListNode findKKth2Tail3(ListNode head, int reciprocal) {
        if (head == null){
            return null;
        }
        if (reciprocal < 0){
            throw new IllegalArgumentException("非法的参数。");
        }
        ListNode preNode = head;
        ListNode lastNode = head;
        for (int i = 0; i <= reciprocal; i++){
            if (preNode != null){
                preNode = preNode.next;
            }else{
                preNode = null;
                break;
            }
        }
        while (preNode != null){
            lastNode = lastNode.next;
            preNode = preNode.next;
        }
        return lastNode;
    }

    /**
     *
     * @param head
     * @param reciprocal
     */
    private static ListNode findKKth2Tail2(ListNode head, int reciprocal) {
        if (head == null){
            return null;
        }
        if (reciprocal < 0){
            throw new IllegalArgumentException("非法的参数。");
        }
        // 快指针
        int count = 0;
        ListNode slow = head;
        ListNode pre = head;
        while (pre.next != null){
            count ++;
            pre = pre.next;
            if (count >= reciprocal){
                slow = slow.next;
            }
        }
        return slow;
    }

    /**
     * 倒数第 ？ 节点
     * 简单的方式，遍历  两次： 第一次获取 链表长度，第二次 获取第 k 个节点
     * @param head
     * @param reciprocal
     */
    private static ListNode findKKth2Tail(ListNode head, int reciprocal) {
        // 获取 链表长度
        int count = 0;
        ListNode curr = head;
        while (curr != null){
            count ++;
            curr = curr.next;
        }
        if (reciprocal > count || reciprocal < 0){
            throw new IllegalArgumentException("非法的参数。");
        }
        // 倒数第 k 个节点，即 顺数  第 n - k + 1 个 为目标节点
        int target = count - reciprocal + 1;
        // 给 count 重新赋值
        count = 1;
        curr = head;
        while (count != target){
            curr = curr.next;
            count++;
        }
        return curr;
    }

    private static ListNode createLinkNode(int len) {
        if (len <= 0){
            throw new IllegalArgumentException("非法的参数。");
        }
        ListNode head = new ListNode();
        head.value = 1;
        ListNode pre = head;
        for (int i = 1; i <=len; i++){
            ListNode next = new ListNode();
            next.value = i+1;
            pre.next = next;
            pre = next;
        }
        return head;
    }

    /**
     * 节点实体
     */
    static class ListNode {
        int value;
        ListNode next;
    }
}
