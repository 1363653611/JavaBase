package zbcn.com.gc;

/**
 * 设置参数: -XX:+PrintCommandLineFlags
 */
public class T01_HelloGC {

    public static void main(String[] args) {
        for (int i=0; i<10000; i++){
            byte[] b = new byte[1024 * 1024];
        }
    }
}
