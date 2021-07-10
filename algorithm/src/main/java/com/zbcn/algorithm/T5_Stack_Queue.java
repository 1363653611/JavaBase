package com.zbcn.algorithm;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class T5_Stack_Queue {


    /**
     * 两个栈 实现一个队列
     */
    static class MyQueue{

        /**
         * 插入栈
         */
        private Stack<Integer> stack1 = new Stack<>();

        /**
         * 删除栈
         */
        private Stack<Integer> stack2 = new Stack<>();

        /**
         * 删出头节点
         */
        public Integer deleteHead(){
            if(stack2.empty()){
                while (!stack1.empty()){
                    stack2.push(stack1.pop());
                }
                if (stack2.empty()){
                    throw new RuntimeException("queue is empty");
                }
                return stack2.pop();
            }else{
                return stack2.pop();
            }
        }

        public void appendTail(Integer value){
            stack1.push(value);
        }
    }

    /**
     * 两个队列实现栈
     */
    static class MyStack{

        /**
         * 插入队列
         */
        private Queue<Integer> queue1;

        /**
         * 删除队列
         */
        private Queue<Integer> queue2;

        public MyStack(int capacity) {
            this.queue1 = new ArrayBlockingQueue(capacity);
            this.queue2 = new ArrayBlockingQueue(capacity);
        }

        public void add(int value){
            if (queue2.isEmpty()){
                queue1.add(value);
            }else {
                queue2.add(value);
            }


        }

        public int get(){
            if (queue1.isEmpty() && queue2.isEmpty()){
                throw new RuntimeException("stack is empty");
            }
            if (queue2.isEmpty()){
                while (queue1.size() != 1){
                    queue2.add(queue1.poll());
                }
                return queue1.poll();
            }else{
                while (queue2.size() != 1){
                    queue1.add(queue2.poll());
                }
                return queue2.poll();
            }

        }
    }



    public static void main(String[] args) {
//        myqueueTest();

        MyStack myStack = new MyStack(3);

        myStack.add(1);
        myStack.add(2);
        myStack.add(3);
        System.out.println(myStack.get());
        System.out.println(myStack.get());
        myStack.add(4);
        myStack.add(5);
        System.out.println(myStack.get());
        System.out.println(myStack.get());
        System.out.println(myStack.get());
    }

    private static void myqueueTest() {
        MyQueue queue = new MyQueue();

        queue.appendTail(1);
        queue.appendTail(2);
        System.out.println(queue.deleteHead());
        queue.appendTail(3);
        queue.appendTail(4);
        queue.appendTail(5);
        System.out.println(queue.deleteHead());
    }
}
