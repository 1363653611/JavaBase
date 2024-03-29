package com.zbcn.thread.pip;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 管道输入/输出流和普通文件的输入/输出流或者网络输入、
 * 输出流不同之处在于管道输入/输出流主要用于线程之间的数据传输，而且传输的媒介为内存。
 *
 * @author Administrator
 * @date 2018/11/9 1:06
 */
public class TestPiple {

    public static void main(String[] args) {
        WriteData writeData = new WriteData();
        ReadData readData = new ReadData();

        PipedInputStream inputStream = new PipedInputStream();
        PipedOutputStream outputStream = new PipedOutputStream();

        try {
            //连接输入输出管道
            outputStream.connect(inputStream);
            //读取 inputStream 数据到 readData
            ThreadRead threadRead = new ThreadRead(readData, inputStream);
            threadRead.start();

            Thread.sleep(2000);
            // 利用 writeData 往 outputStream 管道 写 数据
            ThreadWrite threadWrite = new ThreadWrite(writeData, outputStream);
            threadWrite.start();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
