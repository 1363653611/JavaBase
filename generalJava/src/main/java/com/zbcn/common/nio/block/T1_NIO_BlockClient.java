package com.zbcn.common.nio.block;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 阻塞客户端
 * <br/>
 *
 * @author zbcn8
 * @since 2021/4/10 10:39
 */
public class T1_NIO_BlockClient {
    public static void main(String[] args) {
        try (
             //1. 获取通道
             SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8081));
             // 2. 发送一张图片给服务端
             FileChannel open = FileChannel.open(Paths.get("D:\\照片\\故宫\\wechat\\women.jpg"), StandardOpenOption.READ);
        ) {

            //3.要使用NIO，有了Channel，就必然要有Buffer，Buffer是与数据打交道的呢
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //4.读取本地文件(图片)，发送到服务器
            while (open.read(buffer) != -1) {
                // 在读之前都要切换成读模式
                buffer.flip();
                socketChannel.write(buffer);
                // 读完切换成写模式，能让管道继续读取文件的数据
                buffer.clear();
            }
            //客户端告诉服务端数据已经写完了
            socketChannel.shutdownOutput();

            //客户端接收服务端带过来的数据
            while (socketChannel.read(buffer) != -1){
                //切换为阅读模式
                buffer.flip();
                System.out.println(new String(buffer.array(),0,buffer.limit()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
