package com.zbcn.common.nio.noBlock;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;

/**
 *  设置非阻塞服务端
 *  <br/>
 *  @author zbcn8
 *  @since  2021/4/10 15:43
 */
public class T1_NoBlockServer {
    public static void main(String[] args) {
        try {
            //获取通道
            ServerSocketChannel server = ServerSocketChannel.open();
            //设置为非阻塞模式
            server.configureBlocking(false);
            //绑定链接
            server.bind(new InetSocketAddress(8083));
            //获取选择器
            Selector selector = Selector.open();
            //将通道注册到选择器上,指定接收“监听通道”事件
            server.register(selector, SelectionKey.OP_ACCEPT);
            //轮询的获取选择器上已“就绪”的事件（select()>0）说明已就绪
            while (selector.select() > 0){
                //获取当前选择器上所有注册的“选择键”（已就绪的监听事件）
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                //获取就绪的事件
                while (iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();
                    //接受事件就绪
                    if(selectionKey.isAcceptable()){
                        //获取客户端的链接
                        SocketChannel client = server.accept();
                        //client 模式也设置成 false
                        client.configureBlocking(false);
                        //注册到选择器上--》 拿到客户端的链接为了读取通道的数据（监听读就绪事件）
                        client.register(selector, SelectionKey.OP_READ);

                    }else if (selectionKey.isReadable()){//读事件就绪
                        //获取当前选择器读就绪状态的通道
                        SocketChannel client = (SocketChannel)selectionKey.channel();
                        //client 模式也设置成 false
                        client.configureBlocking(false);
                        //读取数据的 buffer
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        //得到文件通道，将客户端发过来的文件写到本地目录下
                        FileChannel outChannel = FileChannel.open(Paths.get("D:\\照片\\故宫\\wechat\\copy2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
                        while (client.read(byteBuffer)!= -1){
                            //转换为读取模式
                            byteBuffer.flip();
                            outChannel.write(byteBuffer);
                            //转换为写模式
                            byteBuffer.clear();
                        }
                        outChannel.close();
                        client.close();
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
