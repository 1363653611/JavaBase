### 并发容器相关说明 ###
#### ConcurrentHashMap ####

  1. concurrentHashMap 为什么要使用：
   	- answer：
   	 - HashMap 在多线程条件下可能会导致死循环
   	 - HashTable 对所有方法都加了全局锁，导致效率低下。（一个线程插入数据时，另外一个线程查询都不被允许）
   	 - concurrentHashMap 可以解决以上问题。
   	 	1. 使用分段锁 segment(JDK7)
   	 	2. get方法都无需加锁（支持多线程）
   	 	3. 多线程不会出现死循环
   	 	4. segment 插入数据前判断是否要扩容。而 HashMap 是先插入数据，然后判断是否要扩容（如果查出数据后不再使用，则扩容无作用）。
   	 	
  2. concurrentHashMap 的数据结构
  
  3. concurrentHashMap 的初始化
  
  4. 定位Segment 
  
  5. 操作方法: get ,put ...
  
  
### queue ###

  + 队列分为两种
  	- 阻塞算法 （出队和入队都是用锁机制）
  	- 非阻塞算法 （使用CAS 算法实现）
  	
#### ConcurrentLinkedQueue ####

  * 总述： 
 	 1. 使用非阻塞算法（CAS 算法）
 	 2. 继续链接点的线程安全的队列，采用先出先进的规则对节点进行排序
  	 3. 添加元素在尾部，获取元素在头部
  	 4. 无界，线程安全
  
  * ConcurrentLinkedQueue 数据结构
  	
  * 入队列
  * 出队列
  
#### java 中的阻塞队列 （BolockQueue）####

	* 新增两个操作：
	  1. 阻塞的插入 ：当队列满时，队列会阻塞插入元素的线程，直到队列不满
	  2. 阻塞的移除 ：队列为空时，获取元素的线程等待线程变为非空
	  
	  eg：消费者和生产者模式场景
	  
	* 阻塞队列的种类：（7种）
		ArrayBlockQueue 由数组构成的有界阻塞队列
		LinkedBlockQueue 由链表构成额有界阻塞队列
		PriorityBlockQueue 支持优先级排序的无界阻塞队列
		DelayQueue 使用优先级队列实现的无界阻塞队列 （延时队列）
		SynchronousQueue 不存储元素的阻塞队列
		LinkedTransferQueue 链表构成的无界阻塞队列
		LinkedBlockDeQue 链表构成的双向阻塞队列
  
  	
   	 	
