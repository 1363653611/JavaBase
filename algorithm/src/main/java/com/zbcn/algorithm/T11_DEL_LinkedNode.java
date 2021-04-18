package com.zbcn.algorithm;

/**
 *  删除链表的指定节点
 *  <br/>
 *  @author zbcn8
 *  @since  2021/4/13 14:01
 */
public class T11_DEL_LinkedNode {

    public static void main(String[] args) {
        ListNode head = new ListNode();
        head.value = 0;
        ListNode curr = head;
        for (int i = 0; i <= 5; i++){
            curr.next = new ListNode();
            curr.next.value = i + 1;
            curr = curr.next;
        }
        ListNode target = new ListNode();
        target.value = 6;
        ListNode listNode = deleteNode(head, target);
    }


    public static ListNode deleteNode(ListNode head, ListNode target){
        //如果只有一个节点
        if (head== null || target== null){
            return head;
        }
        //如果要删除的节点 不是 尾节点
        if(target.next != null){
            //用当前节点的next 节点的 value 和 next 去替换 target 中的值
            ListNode pNext = target.next;
            target.value = pNext.value;
            target.next = pNext.next;
            pNext.next = null;
        }else if(target.next == null){ // 删除的节点是尾节点
            // 如果头节点也只有一个
            if(head.next == null && head.value == target.value){
                head = null;
            }
            //链表中有多个节点，删除尾部节点
            ListNode preDel = head;
            while (preDel.next.value != target.value){
                preDel = preDel.next;
            }
            preDel.next = null;
        }
        return head;
    }

    static class ListNode {
        int value;
        ListNode next;
    }
}
