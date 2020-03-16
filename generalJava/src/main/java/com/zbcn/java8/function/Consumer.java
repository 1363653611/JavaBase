package com.zbcn.java8.function;

/**
 * 自定义函数接口
 *
 * @author Administrator
 * @date 2019/1/11 17:13
 */
@FunctionalInterface
public interface Consumer<T> {

    void accept(T a);
}
