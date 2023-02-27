package com.cfhui.observer;

/**
 * [客户端]
 * @author cfhui
 * @version V1
 * @date 2023/2/27 下午 5:36
 */
public class Client {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        subject.attach(new ConcreteObserver("name-x", subject));
        subject.attach(new ConcreteObserver("name-y", subject));
        subject.attach(new ConcreteObserver("name-z", subject));
        subject.setSubjectState("ABC");

        subject.notifyObserver();

    }
}
