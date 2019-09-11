### 管道输入、输出 ###

- 管道流作用是线程之间的数据传输

	- 字节： PipedInputStream,PipedOutputStream
	- 字符：PipedRead,PipedWriter
	
- 对于Piped类型的流，必须先要进行绑定，也就是调用connect()方法，如果没有将输入/输出流绑定起来，对于该流的访问将会抛出异常