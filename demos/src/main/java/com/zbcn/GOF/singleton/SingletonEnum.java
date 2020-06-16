package com.zbcn.GOF.singleton;

/**
 * 枚举类模式的单例[极推荐使用]
 *
 * 借助JDK1.5中添加的枚举来实现单例模式。不仅能避免多线程同步问题，而且还能防止反序列化重新创建新的对象
 */
public class  SingletonEnum {

    static enum  SingleEnumHandle {
        //创建一个枚举对象，该对象天生为单例
        INSTANCE;

        private SingletonEnum singletonEnum;

         SingleEnumHandle(){
            singletonEnum = new SingletonEnum();
        }

        public SingletonEnum getInstance(){
            return singletonEnum;
        }
    }

    public static void main(String[] args) {
        SingletonEnum instance = SingleEnumHandle.INSTANCE.getInstance();
    }
}
