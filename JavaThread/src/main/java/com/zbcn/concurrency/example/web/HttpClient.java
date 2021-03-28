package com.zbcn.concurrency.example.web;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class HttpClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8080);
            //获取从键盘输入
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            //获取Socket的输出流，用来发送数据到服务端
            PrintStream out = new PrintStream(socket.getOutputStream());
            //获取socket的输入流来接受服务端发送的数据
            BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            boolean flag = true;
            while(flag){
                System.out.println("请输入信息");
                String s = reader.readLine();
                //发送数据到服务端
                out.println(s);
                if(StringUtils.equals("bye",s)){
                    flag = false;
                }else{
                    try {
                        //从服务器端接收数据有个时间限制（系统自设，也可以自己设置），超过了这个时间，便会抛出该异常
                        String s1 = serverReader.readLine();
                        System.out.println(s1);
                    } catch (SocketTimeoutException e) {
                        System.out.println("Time out, No response");
                        e.printStackTrace();
                    }

                }
            }
            reader.close();
            serverReader.close();
            if(socket !=  null){
                //如果构造函数建立起了连接，则关闭套接字，如果没有建立起连接，自然不用关闭
                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
