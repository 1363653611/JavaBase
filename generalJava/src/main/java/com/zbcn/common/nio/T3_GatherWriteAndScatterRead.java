package com.zbcn.common.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 聚合写入和分散读取
 */
public class T3_GatherWriteAndScatterRead {

    public static void main(String[] args) {
        //scatterRead();

        gatherWrither();
    }

    /**
     * 聚集写入
     */
    private static void gatherWrither() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        RandomAccessFile rfos = null;
        try {
            fis = new FileInputStream("D:\\temp\\lucene-resource\\chinesetext.txt");
            FileChannel inChannel = fis.getChannel();
            //分配指定大小的缓冲区
            ByteBuffer buffer1 = ByteBuffer.allocate(100);
            ByteBuffer buffer2 = ByteBuffer.allocate(1024);
            //分散读取
            ByteBuffer[] bufs = {buffer1,buffer2};
            //聚集写入
            fos = new FileOutputStream("D:\\temp\\lucene-resource\\demoGenerate.txt");
            FileChannel fosChannel = fos.getChannel();
            rfos = new RandomAccessFile("D:\\temp\\lucene-resource\\demo222.txt", "rw");
            FileChannel rosChannel = rfos.getChannel();
            if(inChannel.read(bufs) != -1){
                for (ByteBuffer buf : bufs) {
                    buf.flip();
                }
                fosChannel.write(bufs);
                //调整 position 位置，让其可以重新写
                for (ByteBuffer buf : bufs) {
                    buf.position(0);
                }
                rosChannel.write(bufs);
            }

            fis.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 分散读取
     */
    private static void scatterRead() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("D:\\temp\\lucene-resource\\chinesetext.txt");
            FileChannel inChannel = fis.getChannel();
            //分配指定大小的缓冲区
            ByteBuffer buffer1 = ByteBuffer.allocate(100);
            ByteBuffer buffer2 = ByteBuffer.allocate(1024);
            //分散读取
            ByteBuffer[] bufs = {buffer1,buffer2};
            if(inChannel.read(bufs) != -1){
                for (ByteBuffer buf : bufs){
                    buf.flip();
                    byte[] result = new byte[buf.limit()];
                    buf.get(result);
                    System.out.println("读取的内容：" + new String(result));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
