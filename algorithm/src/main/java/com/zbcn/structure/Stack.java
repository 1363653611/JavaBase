package com.zbcn.structure;

import java.util.Arrays;
import  java.lang.Object;
import java.util.Map;

/**
 *  @title Stack
 *  @Description 栈的模拟操作
 *  小贴士：通常可以利用栈实现字符串逆序，还可以利用栈判断分隔符是否匹配，如<a[b{c}]>，可以正进反出，栈为空则成功。
 *  @author zbcn8
 *  @Date 2020/2/5 21:20
 */
public class Stack {

	/**
	 * 基于数组实现的顺序栈，连续存储的线性实现，需要初始化容量
	 */
	//固定数据类型
	//private int[] array;
	//动态数据类型
	private Object[] objectArray;

	private int maxSize;

	private int top;

	public Stack() {
	}

	public Stack(int maxSize) {
		if(maxSize > 0){
            objectArray = new Object[maxSize];
            this.maxSize = maxSize;
            this.top = -1;
		}else{
			throw new RuntimeException("初始化大小错误：maxSize=" + maxSize);
		}
		this.maxSize = maxSize;
	}

	/**
	 *  入栈
	 * @param obj
	 */
	public void objPush(Object obj){
		grow();//扩容
		//++在前表示先运算再赋值，优先级高，在后表示先赋值再运算，优先级低
		objectArray[++top] = obj;
	}

	public void grow(){
		//<<左移动，1表示乘以2的1次方
		if(top == maxSize-1){
			maxSize = maxSize << 1;
			objectArray = Arrays.copyOf(objectArray, maxSize);
		}
	}

	/**
	 * 出栈
	 * @return
	 */
	public Object objPop(){
		Object o = peekTop();
		//声明原顶栈可以回收空间(GC)
		objectArray[top--] = null;
		return o;
	}

	/**
	 * 查看栈顶
	 * @return
	 */
	public Object peekTop(){
		if(top != -1){
			return objectArray[top];
		}else {
			throw new RuntimeException("stack is null");
		}
	}


	/**基于链式存储，不连续存储的非线性实现**/

	public static class Node<T> {
		private T data;

		private Node<T> next;

		public Node(T data, Node<T> next) {
			this.data = data;
			this.next = next;
		}

		public Node(T obj) {
		}
	}

	/**
	 * 首节点
	 */
	private Node<Object> topNode;


	private int size;

	public void nodePush(Object obj){
		//栈顶指向新元素，新元素的next指向原栈顶元素
		topNode = new Node<Object>(obj,topNode);
		size++;
	}

	public Object nodePop(){
		Node old = this.topNode;
		//栈顶指向下一个元素
		topNode = this.topNode.next;
		//声明原顶栈可以回收空间(GC)
		old.next = null;
		size--;
		return old.data;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[ ");
		for(Node<Object> node = topNode; node != null; node = node.next){
			sb.append(node.data.toString() + " ");
		}
		return sb.toString()+"]";
	}

	public static void main(String[] args) {
		Stack stack = new Stack(1);
        stack.objPush("abc");
        stack.objPush(123);
        stack.objPush("de");
        stack.objPush("cd");
        stack.objPush("er");
        stack.objPush("hello");
        stack.objPush(666);
        stack.objPush(545);
        stack.objPush("word");
        while (stack.top != -1){
            System.out.println(stack.objPop());
        }
        System.out.println(stack.peekTop());

//		Stack stack = new Stack();
//		stack.nodePush("111");
//		stack.nodePush("222");
//		stack.nodePush("aaa");
//		stack.nodePush("bbb");
//		System.out.println(stack);
//		while (stack.size > 1) {
//			System.out.println(stack.nodePop());
//			System.out.println(stack);
//		}
	}

}
