package com.zbcn.common.nio.noBlock;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 *  非阻塞客户端
 *  <br/>
 *  @author zbcn8
 *  @since  2021/4/10 15:33
 */
public class T1_NoBlockClient {

    public static void main(String[] args) {
        try {
            SocketChannel client = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8083));
            //设置成非阻塞模式
            client.configureBlocking(false);
            //发送一张图片给服务端
            FileChannel fileChannel = FileChannel.open(Paths.get("D:\\照片\\故宫\\来自 iOS 设备的照片\\IMG_1251.JPG"), StandardOpenOption.READ);
            //创建与数据交互的换从
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (fileChannel.read(buffer) != -1){
                //设置为读模式
                buffer.flip();
                client.write(buffer);
                //重置point 节点，设置成写模式
                buffer.clear();
            }
            //客户端告诉服务端数据已经写完了
            client.shutdownOutput();
            //关闭流
            fileChannel.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
