package com.zbcn.algorithm;

import java.util.Stack;

public class T5_Stack_Queue {

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


    public static void main(String[] args) {
        T5_Stack_Queue queue = new T5_Stack_Queue();

        queue.appendTail(1);
        queue.appendTail(2);
        System.out.println(queue.deleteHead());
        queue.appendTail(3);
        queue.appendTail(4);
        queue.appendTail(5);
        System.out.println(queue.deleteHead());
    }
}
