package com.zbcn.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * 客户端启动类
 * <br/>
 *
 * @author zbcn8
 * @since 2020/8/5 17:28
 */
public class AppClientHello {
    private final String host;
    private final int port;

    public AppClientHello(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * 配置相应的参数，提供连接到远端的方法
     */
    public void run() throws InterruptedException {
        //I/O线程池
        NioEventLoopGroup group = new NioEventLoopGroup();
        //客户端辅助启动类
        try {
            Bootstrap bs = new Bootstrap();
            bs.group(group).channel(NioSocketChannel.class)//实例化一个Channel
                    .remoteAddress(new InetSocketAddress(host,port))
                    .handler(new ChannelInitializer<SocketChannel>() {//进行通道初始化配置
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //添加我们自定义的Handler
                            socketChannel.pipeline().addLast(new HandlerClientHello());
                        }
                    });
            //连接到远程节点；等待连接完成
            ChannelFuture future=bs.connect().sync();
            //发送消息到服务器端，编码格式是utf-8
            future.channel().writeAndFlush(Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8));
            //阻塞操作，closeFuture()开启了一个channel的监听器（这期间channel在进行各项工作），直到链路断开
            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new AppClientHello("127.0.0.1",18080).run();
    }
}
