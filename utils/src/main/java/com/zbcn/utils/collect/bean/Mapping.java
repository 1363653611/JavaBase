package com.zbcn.utils.collect.bean;

/**
 * Created by zhangquanhong on 2016/6/21.
 */
public interface Mapping<F, T> {

    public T from(F f);
}
