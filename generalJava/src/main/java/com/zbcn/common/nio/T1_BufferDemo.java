package com.zbcn.common.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * buffer测试类
 */
public class T1_BufferDemo {
    public static void main(String[] args) {
        //创建一个缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        System.out.println("初始时的limit (代表当前有多少数据):"+buffer.limit());
        System.out.println("当前的position(缓冲区正在操作的数据位置):"+buffer.position());
        System.out.println("初始容量capacity(缓冲区能够存储数据的最大容量):"+buffer.capacity());
        System.out.println("标记mark,(用来记录当前 position 的位置):"+buffer.mark());

        //添加一些数据到缓冲区
        String s = "hello word";
        buffer.put(s.getBytes());
        //看一下四个核心变量的值
        System.out.println("=--------添加完数据后------------=");
        System.out.println("初始时的limit (代表当前有多少数据):"+buffer.limit());
        System.out.println("当前的position(缓冲区正在操作的数据位置):"+buffer.position());
        System.out.println("初始容量capacity(缓冲区能够存储数据的最大容量):"+buffer.capacity());
        System.out.println("标记mark,(用来记录当前 position 的位置):"+buffer.mark());

        System.out.println("=--------获取完部分数据后------------=");
        Buffer flip = buffer.flip();
        System.out.println("初始时的limit (代表当前有多少数据):"+flip.limit());
        System.out.println("当前的position(缓冲区正在操作的数据位置):"+flip.position());
        System.out.println("初始容量capacity(缓冲区能够存储数据的最大容量):"+flip.capacity());
        System.out.println("标记mark,(用来记录当前 position 的位置):"+flip.mark());

        System.out.println("=--------开始读取数据------------=");
        //创建一个limit 大小的字节数组（因为就只有limit 个数据可读）
        byte[] bytes = new byte[flip.limit()];
        //将数据读取到我们 的字节数组中
        buffer.get(bytes);
        //输出数据
        System.out.println(new String(bytes));
        System.out.println("=--------输出完之后打印------------=");
        System.out.println("get完数据后，初始时的limit (代表当前有多少数据):"+buffer.limit());
        System.out.println("get完数据后，当前的position(缓冲区正在操作的数据位置):"+buffer.position());
        System.out.println("get完数据后，初始容量capacity(缓冲区能够存储数据的最大容量):"+buffer.capacity());
        System.out.println("get完数据后，标记mark,(用来记录当前 position 的位置):"+buffer.mark());

        System.out.println("=--------清理buffer------------=");
        buffer.clear();
        System.out.println("=--------调用完clear清除后------------=");
        System.out.println("clear清除后，初始时的limit (代表当前有多少数据):"+buffer.limit());
        System.out.println("clear清除后，当前的position(缓冲区正在操作的数据位置):"+buffer.position());
        System.out.println("clear清除后，初始容量capacity(缓冲区能够存储数据的最大容量):"+buffer.capacity());
        System.out.println("clear清除后，标记mark,(用来记录当前 position 的位置):"+buffer.mark());
    }
}
