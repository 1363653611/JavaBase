package com.zbcn.utils.function;

/**
 * 2018/5/24
 */
@FunctionalInterface
public interface FallibleSupplier<T> {
    T get() throws Exception;
}
