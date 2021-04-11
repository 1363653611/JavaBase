package com.zbcn.common.nio.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.time.LocalDateTime;
import java.util.Scanner;

public class T6_UDP_Client {
    public static void main(String[] args) {
        try {
            DatagramChannel open = DatagramChannel.open();
            open.configureBlocking(false);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()){
                String next = scanner.next();
                buffer.put((LocalDateTime.now().toString() + ":\n" + next).getBytes());
                buffer.flip();
                open.send(buffer,new InetSocketAddress("127.0.0.1", 8084));
                buffer.clear();
            }
            open.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
