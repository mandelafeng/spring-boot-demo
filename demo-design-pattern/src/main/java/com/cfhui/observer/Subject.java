package com.cfhui.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * [通知者抽象类]
 *
 * @author cfhui
 * @version V1
 * @date 2023/2/27 下午 5:21
 */
abstract class Subject {

    private List<Observer> list = new ArrayList<>();
    /**
     * [增加观察者]
     * @param observer
     * @author cfhui
     * @since V1
     * @date 2023/2/27 下午 5:29
     */
    public void attach(Observer observer) {
        list.add(observer);
    }
    /**
     * [减少观察者]
     * @param observer
     * @author cfhui
     * @since V1
     * @date 2023/2/27 下午 5:29
     */
    public void detach(Observer observer) {
        list.remove(observer);
    }
    /**
     * [通知观察者]
     * @author cfhui
     * @since V1
     * @date 2023/2/27 下午 5:29
     */
    public void notifyObserver() {
        for (Observer observer : list) {
            observer.update();
        }
    }


    protected String subjectState;

    public String getSubjectState() {
        return subjectState;
    }

    public void setSubjectState(String subjectState) {
        this.subjectState = subjectState;
    }

}
