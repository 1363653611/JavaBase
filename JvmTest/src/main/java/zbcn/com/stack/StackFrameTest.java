package zbcn.com.stack;

public class StackFrameTest {

    public static void main(String[] args) {
        method1();
    }

    private static void method1() {
        System.out.println("method1 方法调用开始");
        method2();
        System.out.println("method1 方法调用结束");
    }

    private static void method2() {
        System.out.println("method2 方法调用开始");
        method3();
        System.out.println("method2 方法调用结束");
    }

    private static int method3() {
        System.out.println("method3 方法调用开始");
        int i = 1;
        System.out.println("method3 方法调用结束");
        return i;
    }
}
