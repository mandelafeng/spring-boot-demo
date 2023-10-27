package com.cfhui.chain_of_responsibility.demo1.handler;

import com.cfhui.chain_of_responsibility.demo1.Request;

/**
 * [ 抽象处理者 ]
 * @author cfhui
 * @since V1
 * @date 2023/10/26 上午 9:26
 */
public abstract class Handler {
    /**
     * 下一处理者引用
     */
    private Handler next;
    public Handler(Handler handler){
        this.next = handler;
    }
    public Handler getNext() {
        return next;
    }
    public void setNext(Handler next) {
        this.next = next;
    }
    public abstract boolean process(Request request);
}
