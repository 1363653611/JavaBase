package com.zbcn.java8.function;

/**
 * 函数接口
 *
 * @author Administrator
 * @date 2019/1/11 17:22
 */
@FunctionalInterface
public interface Function<T,R> {

    R apply(T t);
}
