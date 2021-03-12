package com.zbcn.java8.function;

@FunctionalInterface
public interface Converter<F,T> {

    /**
     * 定义一个类型转换功能接口
     * @param from
     * @return
     */
    T convert(F from);
}
