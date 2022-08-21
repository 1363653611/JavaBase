package com.zbcn.test;


import com.zbcn.test.observer.Observable;
import com.zbcn.test.observer.Subscriber;

/**
 * 观察者模式测试：RxJava
 */
public class ObserverTest {
    /**
     * 事件源：用来向 订阅者发布事件。
     */
    static Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<String> subscriber){
            subscriber.onNext("hello RxJava");
            subscriber.onNext("我来测试 观察者模式");
            subscriber.onCompleted();

        }
    });

    /**
     * 创建订阅者：订阅者接受到事件源发布的事件进行处理
     */
    static Subscriber<String> subscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            System.out.println("正规的示例展示：" + s);
        }
    };

    /**
     * HOT observable：
     * @param args
     */
    public static void main(String[] args) {
        observable.setSubscriber(subscriber);
    }
}
