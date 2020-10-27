package com.zbcn.socket.framemessage;

import java.io.*;
import java.net.URL;

/**
 *  侦测测试类
 *  <br/>
 *  @author zbcn8
 *  @since  2020/10/13 19:31
 */
public class Main {


    public static void main(String[] args) {
        delimFramerTest();

        //lengthFramerTest();
    }

    /**
     * 基于长度侦测试
     */
    private static void lengthFramerTest() {
        try {
            URL resource = Main.class.getResource("");
            String path = resource.getPath();
            InputStream inputStream = new FileInputStream(path + File.separator + "msg.txt");
            LengthFramer lengthFramer = new LengthFramer(inputStream);
            FileOutputStream outputStream = new FileOutputStream(path + File.separator + "dest.txt");
            while (true){
                byte[] bytes = lengthFramer.nextMessage();
                if(bytes == null){
                    break;
                }
                lengthFramer.frameMessage(bytes,outputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 分割符侦测测试
     */
    private static void delimFramerTest() {

        try {
            URL resource = Main.class.getResource("");
            String path = resource.getPath();
            InputStream inputStream = new FileInputStream(path + File.separator + "msg.txt");
            Frame delim = new DelimFramer(inputStream);
            FileOutputStream outputStream = new FileOutputStream(path + File.separator + "dest.txt");
            while (true){
                byte[] bytes = delim.nextMessage();
                if(bytes == null){
                    break;
                }
                delim.frameMessage(bytes,outputStream);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
