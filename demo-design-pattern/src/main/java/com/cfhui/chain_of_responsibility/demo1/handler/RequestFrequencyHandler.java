package com.cfhui.chain_of_responsibility.demo1.handler;

import com.cfhui.chain_of_responsibility.demo1.Request;

/**
 * [ 具体处理者 请求频率处理器]
 * @author cfhui
 * @since V1
 * @date 2023/10/26 上午 9:26
 */
public class RequestFrequencyHandler extends Handler{
    public RequestFrequencyHandler(Handler handler) {
        super(handler);
    }
    @Override
    public boolean process(Request request) {
        System.out.println("开始执行请求频率....");
        //1、判断请求频率是否符合规则
        if(request.isRequestFrequency()){
            System.out.println("执行请求频率正常，通过！");
            Handler handler = this.getNext();
            //表示通过
            if (handler != null) {
                //2、执行其下一个处理器(登陆验证)
                if (!handler.process(request)) {
                    return false;
                }
            }
            return true;
        }
        //请求频率过多失败
        System.out.println("请求频率过多失败!");
        return false;
    }
}
