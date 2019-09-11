package com.zbcn.concurrency.example.web;

import com.zbcn.concurrency.example.threadpool.DefaultThreadPool;
import com.zbcn.concurrency.example.threadpool.ThreadPool;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName: SimpleHttpServer
 * @Description: 一个简单的多线程服务器
 * @author Administrator
 * @date 2019-07-01 16:10
 *
 */
public class SimpleHttpServer {
	
	//处理http请求的线程池
	static ThreadPool<HttpRequestHandle> threadPool = new DefaultThreadPool<HttpRequestHandle>(1);
	//http请求的根路径
	static String basePath;
	
	static ServerSocket serverSocket;
	//服务监听端口
	static int port = 8080;
	
	public static void setPort(int port) {
		if(port > 0) {
			SimpleHttpServer.port = port;
		}
	}
	
	public static void setBasePath(String basePath) throws Exception {
		if (basePath != null && new File(basePath).exists() && new File(basePath).isDirectory()) {
			SimpleHttpServer.basePath = basePath;
		}
	}
	
	// 启动SimpleHttpServer
	public static void start() throws Exception {
		serverSocket = new ServerSocket(port);
		Socket socket = null;
		while ((socket = serverSocket.accept()) != null) {
			// 接收一个客户端Socket，生成一个HttpRequestHandler，放入线程池执行
			threadPool.execute(new HttpRequestHandle(socket));
		}
		serverSocket.close();
	}
	
	
	
	static class HttpRequestHandle implements Runnable{

		private Socket socket;
		
		public HttpRequestHandle(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			String line = null;
			BufferedReader br = null;
			BufferedReader reader = null;
			PrintWriter out = null;
			InputStream in = null;
			
			try {
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				//请求头
				String header = reader.readLine();
				//由相对路径计算出绝对路径
				String filePath = basePath + header.split(" ")[1];
				out = new PrintWriter(socket.getOutputStream());
				// 如果请求资源的后缀为jpg或者ico，则读取资源并输出
				if(filePath.endsWith("jpg") || filePath.endsWith("ico")) {
					in = new FileInputStream(filePath);
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					int i = 0;
					while ((i = in.read())!= -1) {
						baos.write(i);
					}
					byte[] array = baos.toByteArray();
					out.println("HTTP/1.1 200 OK");
					out.println("Server: Molly");
					out.println("Content-Type: image/jpeg");
					out.println("Content-Length: " + array.length);
					out.println("");
					socket.getOutputStream().write(array, 0, array.length);
				}else {
					br = new BufferedReader(new InputStreamReader(new
							FileInputStream(filePath)));
					out = new PrintWriter(socket.getOutputStream());
					out.println("HTTP/1.1 200 OK");
					out.println("Server: Molly");
					out.println("Content-Type: text/html; charset=UTF-8");
					out.println("");
					while ((line = br.readLine()) != null) {
					out.println(line);
					}
					out.flush();
				}
			} catch (IOException e) {
				out.println("HTTP/1.1 500");
				out.println("");
				out.flush();
			}finally {
				close(br, in, reader, out, socket);
			}
			
			
		}
		
		// 关闭流或者Socket
		private static void close(Closeable... closeables) {
			if (closeables != null) {
				for (Closeable closeable : closeables) {
					try {
						closeable.close();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}
		
	}
}
