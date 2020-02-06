package com.zbcn.structure;
/**
 *  @title LinkedList
 *  @Description 链表数据结构
 *  @author zbcn8
 *  @Date 2020/2/6 12:12
 */
public class LinkedList {

	private class Node<T>{

		private T data;
		//上一个
		private Node prev;
		//下一个
		private Node next;

		public Node(T data) {
			this.data = data;
		}
	}

	/**
	 * 链表通常是由一连串节点组成，每个节点包含任意的实例数据（data fields）， 和一个用来指向上一个元素，一个用来指向下一个元素的指针。
	 */
	private Node head; //链表头
	private Node tail; //链表尾
	private int size; //节点数

	public LinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	/**
	 * 头部添加元素
	 * @param obj
	 */
	public void addHead(Object obj){
		Node<Object> node = new Node<>(obj);
		if(size == 0){
			head = node;
			tail = node;
			size++;
		}else{
			//在头部添加节点
			head.prev= node;
			node.next = head;
			head = node;
			size++;
		}
	}

	/**
	 * 获取头部元素
	 * @return
	 */
	public Object getHead(){
		//头部指向下一个，prev值为null则说明是链表的头部
		if(size <= 0){
			throw new RuntimeException("linkedList is null");
		}
		Node node = head;
		head.prev = null;
		head = head.next;
		node.next = null;
		return node.data;
	}

	/**
	 * 尾部添加元素
	 * @param obj
	 */
	public void addTail(Object obj){
		Node node = new Node(obj);
		if(size == 0){
			head = node;
			tail = node;
			size++;
		}else{
			node.prev = tail;
			tail.next = node;
			tail = node;
			size++;
		}
	}

	/**
	 *  @title LinkedList
	 *  @Description 尾部获取
	 *  @author zbcn8
	 *  @Date 2020/2/6 13:19
	 */
	public Object getTail(){
		if(size <= 0){
			throw new RuntimeException("linkedList is null");
		}
		Node node = tail;
		tail = tail.prev;
		size--;
		node.prev = null;
		return node.data;
	}

	/**
	 * 显示数据
	 */
	public void display(){
		Node node = head;
		int localSize = this.size;
		while (localSize > 0){
			System.out.print("["+node.data+"->");
			node = node.next;
			localSize--;
		}
		System.out.println("");
	}

	public static void main(String[] args) {
		LinkedList linkedList = new LinkedList();
		linkedList.addHead("123");
		linkedList.addHead("abc");
//        linkedList.addHead("%$$");
//        linkedList.addTail("+_+");
        linkedList.addTail("hello");
		linkedList.addTail("word");
		linkedList.display();
		System.out.println(linkedList.getHead());
		System.out.println(linkedList.getTail());
	}
}
