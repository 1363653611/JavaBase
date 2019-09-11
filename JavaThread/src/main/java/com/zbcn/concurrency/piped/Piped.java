package com.zbcn.concurrency.piped;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @ClassName: Piped
 * @Description: 管道流：线程之间的数据传输
 * @author Administrator
 * @date 2019-06-27 11:06
 *
 */
public class Piped {

	public static void main(String[] args) throws IOException {
		PipedWriter out = new PipedWriter();
		PipedReader in = new PipedReader();
		// 将输出流和输入流进行连接，否则在使用时会抛出IOException
		out.connect(in);
		Thread thread = new Thread(new Print(in), "printThread");
		thread.start();
		int receive = 0;
		try {
			while ((receive = System.in.read()) != -1) {
				out.write(receive);
			}
		} finally {
			out.close();
		}
	}

	static class Print implements Runnable {

		private PipedReader in;

		public Print(PipedReader in) {
			this.in = in;
		}

		@Override
		public void run() {
			int receive = 0;
			try {
				while ((receive = in.read()) != -1) {
					System.out.print((char) receive);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// TODO Auto-generated method stub

		}

	}
}
