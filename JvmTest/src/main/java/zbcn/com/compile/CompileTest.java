package zbcn.com.compile;

public class CompileTest {


    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;

        //自动装箱拆箱
        System.out.println(c == d);//true

        // 大于-127~128  的自动装箱和拆箱不起作用
        System.out.println(e == f); // false

        System.out.println(c == (a+b));
        System.out.println(c.equals((a+b)));
        //自动转换为int类型比较
        System.out.println(g == (a+b));
        //对象比较不相等
        System.out.println(g.equals((a+b)));
    }
}
