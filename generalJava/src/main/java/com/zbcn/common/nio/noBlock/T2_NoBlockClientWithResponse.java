package com.zbcn.common.nio.noBlock;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;

/**
 *  非阻塞客户端
 *  <br/>
 *  @author zbcn8
 *  @since  2021/4/10 15:33
 */
public class T2_NoBlockClientWithResponse {

    public static void main(String[] args) {
        try {
            SocketChannel client = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8083));
            //设置成非阻塞模式
            client.configureBlocking(false);

            //获取监听器：用来获取服务端发送数据
            Selector selector = Selector.open();
            //将通道注册到选择器中
            client.register(selector, SelectionKey.OP_READ);

            //发送一张图片给服务端
            FileChannel fileChannel = FileChannel.open(Paths.get("D:\\照片\\故宫\\wechat\\women.jpg"), StandardOpenOption.READ);
            //创建与数据交互的换从
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (fileChannel.read(buffer) != -1){
                //设置为读模式
                buffer.flip();
                client.write(buffer);
                //重置point 节点，设置成写模式
                buffer.clear();
            }
            fileChannel.close();
            //客户端告诉服务端数据已经写完了
            client.shutdownOutput();

            //获取服务器上返回的信息
            //轮训的获取选择器上已经 “就绪” 的事件 ----》只要 select.select() > 0, 说明已经就绪
            while (selector.select() > 0){
                //获取当前选择器所注册的 “注册键” （已就绪的监听事件）
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                //获取就绪的事件，不同的事件处理不同的事
                while (iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();
                    //读事件就绪
                    if(selectionKey.isReadable()){
                        SocketChannel channel = (SocketChannel)selectionKey.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        //读取服务端响应的数据
                        int read = channel.read(byteBuffer);
                        if (read > 0){
                            //设置为读模式
                            byteBuffer.flip();
                            System.out.println(new String(byteBuffer.array(),0,read));
                        }
                        channel.close();
                    }
                    iterator.remove();
                }
                //关闭流
                selector.close();
            }

            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
