package zbcn.com.arthas;

import java.util.concurrent.TimeUnit;
/**
 *  测试 arthas 功能的用例
 *
 *  <br/>
 *  @author zbcn8
 *  @since  2020/7/30 19:42
 */
public class ArthasTest {

    public static void main(String[] args) throws InterruptedException {
        while (true) {
             TimeUnit.SECONDS.sleep(5);
            new Hello().sayHello();
        }
    }
}
