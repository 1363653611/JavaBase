package com.zbcn.common.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class T2_Channel {

    //使用FileChannel配合缓冲区实现文件复制的功能
    public static void main(String[] args) {
        //copyFile();
        //copyFile2();

        copyFile3();
    }

    //通道之间通过transfer()实现数据的传输(直接操作缓冲区)：
    private static void copyFile3() {
        try {
            FileChannel inChannel = FileChannel.open(Paths.get("D:\\照片\\故宫\\wechat\\women.jpg"), StandardOpenOption.READ);
            FileChannel outChannel = FileChannel.open(Paths.get("D:\\照片\\故宫\\2.jpg"), StandardOpenOption.WRITE,
                    StandardOpenOption.READ,StandardOpenOption.CREATE_NEW);
            //outChannel.transferFrom(inChannel, 0,inChannel.size());
            inChannel.transferTo(0, inChannel.size(), outChannel);
            inChannel.close();
            outChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //使用内存映射文件的方式实现文件复制的功能(直接操作缓冲区)
    private static void copyFile2() {
        try {
            FileChannel inChannel = FileChannel.open(Paths.get("D:\\照片\\故宫\\wechat\\women.jpg"), StandardOpenOption.READ);
            FileChannel outChannel = FileChannel.open(Paths.get("D:\\照片\\故宫\\2.jpg"), StandardOpenOption.WRITE,
                    StandardOpenOption.READ,StandardOpenOption.CREATE_NEW);
            //内存映射文件
            MappedByteBuffer inMappedBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
            MappedByteBuffer outMappedBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());
            //直接对缓冲区进行数据的读写
            byte[] dst= new byte[inMappedBuffer.limit()];
            inMappedBuffer.get(dst);
            outMappedBuffer.put(dst);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copyFile() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        //创建通道
        FileChannel inChannel = null;
        FileChannel outChannel = null;

        try {
            fis = new FileInputStream("D:\\照片\\故宫\\wechat\\women.jpg");
            fos = new FileOutputStream("D:\\照片\\故宫\\2.jpg");
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();

            //分配指定大小的缓冲
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //将通道中的数据存储缓冲区buffer中
            while (inChannel.read(buffer) != -1){
                //切换数据的读取模式
                buffer.flip();
                //将数据与输出管道链接
                outChannel.write(buffer);
                //清空缓存
                buffer.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
