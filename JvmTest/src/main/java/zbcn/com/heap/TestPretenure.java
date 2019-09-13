package zbcn.com.heap;

/**
 * vm args : -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 *
 * -XX:PretenureSizeThreshold=3145728   (3M)设置后，大于3M的对象直接分配到老年区（后面的值不能直接设置为3M）
 * -XX:+PrintTenuringDistribution
 */
public class TestPretenure {

    private static final int _1MB = 1024 * 1024;


    public static void main(String[] args) {
        testPretenureSizeThreShold();
    }

    public static void testPretenureSizeThreShold(){
        byte[] allocation;

        allocation = new byte[4 * _1MB];//直接分配到老年代
    }
}
