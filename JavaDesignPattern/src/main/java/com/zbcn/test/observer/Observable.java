package com.zbcn.test.observer;

/**
 * 事件源
 * @param <T>
 */
public class Observable<T> {

    private OnSubscribe<T> onSubscribe;

    public static <T> Observable create(OnSubscribe<T> onSubscribe){
        Observable<T> tObservable = new Observable<>();
        tObservable.setOnSubscribe(onSubscribe);
        return tObservable;
    }

    public OnSubscribe<T> getOnSubscribe() {
        return onSubscribe;
    }

    public void setOnSubscribe(OnSubscribe<T> onSubscribe) {
        this.onSubscribe = onSubscribe;
    }

    /**
     * 观察者订阅之后，直接出发通知
     * @param subscriber
     */
    public void setSubscriber(Subscriber<T> subscriber){
        onSubscribe.call(subscriber);
    }

    /**
     * 观察者调用者
     * @param <T>
     */
    public interface OnSubscribe<T>{

        public void call(Subscriber<T> subscriber);
    }
}
