package com.zbcn.common.nio.block;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *  nio 阻塞服务端
 *  <br/>
 *  @author zbcn8
 *  @since  2021/4/10 10:50
 */
public class T2_NIO_BlockServer {
    public static void main(String[] args) {
        try {
            //1.获取通道
            ServerSocketChannel server = ServerSocketChannel.open();
            //2.得到文件通道，将客户端传递过来的图片写到本地项目下(写模式、没有则创建)
            FileChannel fileChannel = FileChannel.open(Paths.get("D:\\照片\\故宫\\wechat\\copy.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
            //3.绑定链接
            server.bind(new InetSocketAddress(8081));
            //4. 获取客户端的连接(阻塞的)
            SocketChannel client = server.accept();
            //5. 要使用NIO，有了Channel，就必然要有Buffer，Buffer是与数据打交道的呢
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //6.将客户端传递过来的图片保存在本地中
            while (client.read(buffer) != -1){
                //修改为读模式
                buffer.flip();
                fileChannel.write(buffer);
                // 读完切换成写模式，能让管道继续读取文件的数据
                buffer.clear();
            }
            //此时服务端保存完图片想要告诉客户端已经收到图片
            buffer.put("img is success".getBytes(Charset.defaultCharset()));
            buffer.flip();
            client.write(buffer);
            buffer.clear();

            // 7.关闭通道
            fileChannel.close();
            client.close();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
