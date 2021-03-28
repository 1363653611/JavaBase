package com.zbcn.concurrency.example.web;

import com.zbcn.concurrency.example.threadpool.DefaultThreadPool;
import com.zbcn.concurrency.example.threadpool.ThreadPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

/**
 * @ClassName: SimpleHttpServer
 * @Description: 一个简单的多线程服务器
 * @author Administrator
 * @date 2019-07-01 16:10
 *
 */
@Slf4j
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
		//创建一个socket 服务端
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
			BufferedReader br = null;
			BufferedReader reader = null;
			PrintWriter out = null;
			InputStream in = null;
			try {
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				//请求头
				String line = reader.readLine();
				String header = null;
				while (StringUtils.isNotBlank(line)){
					log.info("客户短发送信息：{}", line);
					//读取第一行为header
					line = reader.readLine();
					if(StringUtils.isBlank(header)){

					}
				}
				//由相对路径计算出绝对路径
				String filePath = basePath + File.separator + header;
				out = new PrintWriter(socket.getOutputStream());
				// 如果请求资源的后缀为jpg或者ico，则读取资源并输出
				if(header.endsWith(".jpg") || header.endsWith(".ico")) {
					in = System.in;
					reader = new BufferedReader(new InputStreamReader(in));
					String str = reader.readLine();
					out.write(str);
					out.println("HTTP/1.1 200 OK");
					out.println("Server: Molly");
					out.println("Content-Type: image/jpeg");
					out.println("Content-Length: " + StringUtils.length(str));
					out.println("");
				}else {
					br = new BufferedReader(new InputStreamReader(System.in));
					out = new PrintWriter(socket.getOutputStream());
					out.println("HTTP/1.1 200 OK");
					out.println("Server: Molly");
					out.println("Content-Type: text/html; charset=UTF-8");
					out.println("");
					while ((line = br.readLine()) != null) {
						if(line.equals("exit")){
							break;
						}
						out.write(line);
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
						if(Objects.nonNull(closeable)){
							closeable.close();
						}

					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}
		
	}

	public static void main(String[] args) {
		try {
			SimpleHttpServer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
