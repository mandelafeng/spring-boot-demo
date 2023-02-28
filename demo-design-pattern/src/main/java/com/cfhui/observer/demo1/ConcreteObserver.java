package com.cfhui.observer.demo1;

/**
 * @author cfhui
 * @version V1
 * @description TODO
 * @date 2023/2/27 下午 5:34
 */
public class ConcreteObserver extends Observer{
    private String name;
    private Subject subject;

    public ConcreteObserver(String name, Subject subject) {
        this.name = name;
        this.subject = subject;
    }

    @Override
    public void update() {
        System.out.println("观察者" + this.name + "的新状态是" + this.subject.getSubjectState());
    }
}
