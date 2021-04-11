package com.zbcn.common.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class T5_PipIo {

    public static void main(String[] args) throws IOException {
        //获取管道
        Pipe pipe = Pipe.open();
        Thread A = new Thread(() -> {
            System.out.println("进入写数据方法");
            //将缓冲区的数据写入管道
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            Pipe.SinkChannel sink = pipe.sink();
            byteBuffer.put("通过单项管道发送数据".getBytes());
            byteBuffer.flip();
            try {
                sink.write(byteBuffer);
                sink.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread B = new Thread(() -> {
            System.out.println("进入读数据方法");
            //读取缓冲区中的数据
            Pipe.SourceChannel source = pipe.source();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            try {
                source.read(byteBuffer);
                byteBuffer.flip();
                System.out.println(new String(byteBuffer.array(), 0, byteBuffer.limit()));
                source.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        A.start();
        B.start();
    }
}
