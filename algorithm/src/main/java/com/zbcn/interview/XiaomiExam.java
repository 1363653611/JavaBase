package com.zbcn.interview;

/**
 * 重排链表
 *  要求使用原地算法，不能改变节点内部的值，需要对实际的节点进行交换
 *
 *  给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 *  <br/>
 *  @author zbcn8
 *  @since  2021/3/11 22:59
 */
public class XiaomiExam {

    public static void main(String[] args) {
        LinkNode linkNode = buildLinkNode();
        reorderListNode(linkNode);
    }

    /**
     * 重排 列表
     * @param node
     */
    private static void reorderListNode(LinkNode node) {
        //链表的数量小于3 ，则无需重排
        if(node == null || node.next == null || node.next.next == null){
            return;
        }
        //使用快慢指针法将链表 折半 分为部分，
        LinkNode slow = node;
        LinkNode fast = node;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        LinkNode headList = node;
        LinkNode tailList = slow.next;
        slow.next = null;
        //反转尾部链表
        tailList = reverse(tailList);
        insert(headList,tailList);
        System.out.println(node);
    }

    private static void insert(LinkNode headList, LinkNode tailList) {
        LinkNode node1 = headList;
        LinkNode node2 = tailList;
        //node2 始终减少，插入到node1 中去
        while(node2 != null){
            //临时保存当前节点的下一个节点
            LinkNode nodeNext1 = node1.next;
            LinkNode nodeNext2 = node2.next;

            //让当前的node1 的next 指向node2
            node1.next = node2;
            //让node2 的 next 指向 临时的nodeNext1
            node2.next = nodeNext1;

            // 让node1指向刚才的nodenext1
            node1 = nodeNext1;
            //让node2指向刚才的nodenext2
            node2 = nodeNext2;
        }
    }

    private static LinkNode reverse(LinkNode head) {
        //定义一个链表存储反转后的数据
        LinkNode dest = new LinkNode(0);
        //保存当前head 的 值
        LinkNode node = head;
        while(node != null){
           LinkNode temp =  node.next;
           node.next = dest.next;
           dest.next = node;
           node = temp;
        }
        return dest.next;
    }

    public static LinkNode buildLinkNode(){
        LinkNode head = new LinkNode(1);
        LinkNode current = head;
        for (int i = 2;i <= 6; i++){
            LinkNode next = new LinkNode(i);
            current.next = next;
            current = next;
        }
        return head;

    }


    static class LinkNode {
        int val;
        LinkNode next;

        public LinkNode(int val) {
            this.val = val;
        }
    }

}
