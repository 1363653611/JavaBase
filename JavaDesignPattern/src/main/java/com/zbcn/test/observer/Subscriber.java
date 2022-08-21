package com.zbcn.test.observer;

/**
 * 订阅或者观察者
 */
public interface Subscriber<T> {
    public void onCompleted();

    public void onError(Throwable e);

    public void onNext(T t);
}
