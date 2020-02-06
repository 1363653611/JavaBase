package com.zbcn.structure;

/**
 *  @title Queue
 *  @Description 自定义队列
 *  @author zbcn8
 *  @Date 2020/2/5 23:33
 */
public class Queue {

	/**
	 * 单向队列（queue）：只能在一端插入数据，另一端删除数据
	 * 双向队列（queue）：没一端都可以插入和删除数据操作
	 * 与栈不同的是，队列中的数据总不是从下标0 开始的。
	 * 选择的做法是移动队列头和队列尾的指针
	 * 为了避免队列不满却不能插入新的数据，我们可以让队尾指针绕回到数组的开始位置，这也称之为“循环队列”
	 */
	// 单向循环队列，顺序存储结构实现
	private Object[] objQueue;

	//队列大小
	private int maxSize;

	//顶部
	private int top;

	//底部
	private int bottom;

	//实际元素
	private int item;

	public Queue(int maxSize) {
		this.maxSize = maxSize;
		objQueue = new Object[maxSize];
		top = 0;
		bottom = -1;
		item = 0;
	}

	/**
	 * 添加元素:入队
	 * @param obj
	 */
	public void add(Object obj){
		if(item == maxSize){
			throw new RuntimeException(obj+" add error, queue is full");
		}
		//循环队列，首尾结合，下标控制队首和队尾位置
		if(bottom == maxSize - 1){
			bottom = -1;
		}
		objQueue[++bottom] = obj;
		item++;
	}

	public Object out(){
		if(item == 0){
			throw new RuntimeException("queue is null");
		}
		Object obj = objQueue[top];
		//声明原顶栈可以回收空间(GC)
		objQueue[top] = null;
		top++;
		//重置下标
		if(top == maxSize){
			top = 0;
		}
		item--;
		return obj;
	}

	private class NodeQueue<T>{
		private T data;

		private NodeQueue next;

		public NodeQueue(T data, NodeQueue next) {
			this.data = data;
			this.next = next;
		}

	}

	//队列头 出
	private NodeQueue queueTop;

	//队列尾 进
	private NodeQueue queueBottom;

	//队列大小
	private int size;

	public Queue(){
		queueTop = null;
		queueBottom = null;
		size = 0;
	}

	/**
	 * 入队
	 * @param obj
	 */
	public void addNodeQueue(Object obj){
		if(size == 0){
			queueTop = new NodeQueue(obj,null);
			//指向同一地址
			queueBottom = queueTop;
		}else{
			NodeQueue<Object> nodeQueue = new NodeQueue<>(obj, null);
			//让尾节点的next 节点指向 新添加的节点
			queueBottom.next = nodeQueue;
			//以新增节点作为尾节点
			queueBottom = nodeQueue;
		}
		size++;
	}

	/**
	 * 出队
	 * @return
	 */
	public Object outNode(){
		//判断队列是否为空
		if(size == 0){
			throw new RuntimeException("the queue is empty");
		}
		//需要出的队列
		NodeQueue node = queueTop;
		// 更新topQueue
		queueTop = queueTop.next;
		//声明原队列头next可以回收空间(GC)
		node.next = null;
		size--;
		return node.data;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder("{ ");
		for (NodeQueue nodeQueue = queueTop; nodeQueue != null; nodeQueue = nodeQueue.next ){
			sb.append(nodeQueue.data.toString()+" ");
		}
		return sb.toString()+"}";
	}

	public static void main(String[] args) {
		Queue queue = new Queue();
		queue.addNodeQueue("123");
		queue.addNodeQueue("abc");
		queue.addNodeQueue("ddd");
		System.out.println(queue);
		System.out.println(queue.outNode());
		System.out.println(queue);
		queue.outNode();
		queue.outNode();
		System.out.println(queue);
	}

}
