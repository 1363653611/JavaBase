package com.zbcn.GOF.singleton;

/**
 * 饿汉式单例模式
 */
public class SingletonEHan {

    private static SingletonEHan singletonEHan = new SingletonEHan();

    private SingletonEHan() {
    }

    /**
     * 1.单例模式的饿汉式[可用]
     * @return
     */
    public static SingletonEHan getInstance(){
        return singletonEHan;
    }

    /**
     * 2. 单例模式的饿汉式变换写法[可用]
     * 基本没区别
     */
    private static SingletonEHan singletonEHanTwo = null;

    static {
        singletonEHanTwo = new SingletonEHan();
    }

    public static SingletonEHan getSingletonEHan() {
        if (singletonEHanTwo == null) {
            singletonEHanTwo = new SingletonEHan();
        }
        return singletonEHanTwo;
    }
}
