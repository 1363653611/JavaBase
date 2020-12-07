package com.zbcn.socket.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *  手写web 服务器，主要是为了了解 web 服务 和http 的内部实现原理
 *  <br/>
 *  @author zbcn8
 *  @since  2020/12/1 16:08
 */
public class MyTomcat {

    public static void main(String[] args) {
        MyTomcat myTomcat = new MyTomcat();
        myTomcat.start();
    }

    /**
     * 开启一个socket 服务
     */
    private void start(){
        try {
            //开启一个 Socket 服务端，并监听 8090 端口
            ServerSocket serverSocket = new ServerSocket(8090);
            do{
                //阻塞，直到有客户端连接上，才会执行后面的逻辑
                Socket accept = serverSocket.accept();
                accept.setSoTimeout(1000);
                accept.setKeepAlive(false);
                //处理数据
                handle(accept);
                accept.close();
            }while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * http response
     *  第一行 协议 返回状态
     *  第二行 媒体类型 josn/html
     *  第三行 空
     *  内容
     * @param accept
     */
    private void handle(Socket accept) {

        //拼接返回的 request 报文
        StringBuilder responseBuilder = new StringBuilder();
        responseBuilder
                //返回 200 状态码，表示请求成功
                .append("HTTP/1.1 200 OK \r\n")
                //告诉请求的客户端，返回的内容是 text/html 格式的
                .append("Content-Type: text/html; charset=UTF-8 \r\n")
                .append("Cache-Control: no-cache \r\n")
                .append("Connection: close \r\n")
                //首部字段和消息实体中间的空行
                .append("\r\n")
                //内容部分
                .append("hello tomcat");
        try (
                //取得对方socket的输入流并对其操作封装为对象流，但如果对方socket不先输出的话
                //是无法取得该输入流的，这样会一直处理阻塞状态，界面会卡住
                //获取客户端通道的输入流
                InputStream inputStream = accept.getInputStream();
                //获取客户端通道的输出流
                OutputStream outputStream = accept.getOutputStream()
                ){
//            List<String> list = IOUtils.readLines(inputStream, "utf-8");
//            for (String s : list) {
//                System.out.println("请求内容： "+s);
//            }
            /**
             * 值得注意的是，如果接受的网页数据量很大，先把这些数据全部保存在
             * ByteArrayOutputStream的缓存中不是明智的做法，因为这些数据
             * 会占用大量的内存。更有效的方法是利用scanner来读取网页数据：
             */
//            Scanner scannerSocket=new Scanner(inputStream);
//            String data;
//            while (scannerSocket.hasNextLine()){
//                data=scannerSocket.nextLine();
//                System.out.println(data);
//            }

            byte[] bytes = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = inputStream.read(bytes)) != -1) {
                //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                sb.append(new String(bytes, 0, len,"UTF-8"));
            }
            System.out.println("get message from client: " + sb);
            // //往输出流通道写消息
            outputStream.write(responseBuilder.toString().getBytes());
            //流是有缓存机制的，写消息的时候不一定立马发出去，刷一下才能保证数据发送出去
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
