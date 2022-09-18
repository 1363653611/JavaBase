package com.zbcn.algorithm;

/**
 * 队列练习
 */
public class T21_QueueTest {

    /**
     * 用数组实现队列
     */
    public static class ArrayQueue{
        /**
         * 队列， 用来存储数据
         */
        private String[] items;

        /**
         * 数组大小，表示队列的大小
         */
        private int n = 0;

        /**
         * 队列头部
         */
        private int head = 0;

        /**
         * 队列尾部
         */
        private int tail = 0;


        /**
         * 申请 一个 capacity 大小的队列
         * @param capacity
         */
        public ArrayQueue(int capacity){
            items = new String[capacity];
            n = capacity;
        }

        /**
         * 入队
         * @param item
         * @return
         */
        public boolean enqueue(String item){

            // if tail = n; 表示队列末尾没有空间了
            if(tail == n){
                // 表示队列占满了
                if (head == 0){
                    return  false;
                }
                // 数据搬迁
                for (int i = head; i<tail; i++){
                    items[i-head] = items[i];
                }
                //搬迁完成后，更新head 和 tail
                tail -= head;
                head = 0;

            }
            items[tail] = item;
            tail++;
            return true;
        }


        public String dequeue(){
            // if tail = head, 表示 队列已经空了
            if (tail == head){
                return null;
            }
            // 获取结果
            String res = items[head];
            head++;
            return res;
        }
    }


    /**
     * 链式队列
     */
    public static class LinkedQueue{
        static class Node{
            /**
             * 数据
             */
            String  data;

            /**
             * 下一个节点
             */
            Node next;

            public Node(String data){
                this.data = data;
            }
        }

        private Node head;

        private Node tail;

        /**
         * 记录元素的个数
         */
        private int n;


        public boolean enqueue(String item){
            if (tail == null){
                tail = new Node(item);
                head = tail;
                n++;
                return true;
            }
            tail.next = new Node(item);
            tail = tail.next;
            n++;
            return true;
        }

        public String dequeue(){
            if (head == null){
                return  null;
            }
            Node res = head;
            head = head.next;
            n--;
            return res.data;
        }

    }

    /**
     * 循环队列
     */
    static class CircularQueue{
        private String[] items;

        /**
         * 头
         */
        private int head;
        /**
         * 尾
         */
        private int tail;

        /**
         * 队列长度
         */
        private int  n;

        public CircularQueue(int capacity){
            items = new String[capacity];
            n = capacity;
        }


        public boolean enqueue(String item){
            // 判断队列是否已经满了
            if ((tail  + 1) % n == head){
                return false;
            }
            items[tail] = item;
            tail = (tail+1) % n;
            return true;
        }

        public String dequeue(){
            // 判断队列是否为空
            if (tail == head){
                return null;
            }
            String res = items[head];
            head = (tail + 1) % n;
            return res;
        }
    }


}
