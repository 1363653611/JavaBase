package com.zbcn.common.nio.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

public class T7_UDP_Server {

    public static void main(String[] args) {
        try {
            DatagramChannel dc = DatagramChannel.open();
            dc.bind(new InetSocketAddress(8084));
            dc.configureBlocking(false);
            Selector selector = Selector.open();
            dc.register(selector, SelectionKey.OP_READ);
            while (selector.select() > 0){
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey sk = iterator.next();
                    if (sk.isReadable()){
                        ByteBuffer allocate = ByteBuffer.allocate(1024);
                        dc.receive(allocate);
                        allocate.flip();
                        System.out.println(new String(allocate.array(),0, allocate.limit()));
                        allocate.clear();
                    }
                    iterator.remove();
                }
            dc.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
