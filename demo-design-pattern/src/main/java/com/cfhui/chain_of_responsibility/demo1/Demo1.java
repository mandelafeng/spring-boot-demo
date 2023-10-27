package com.cfhui.chain_of_responsibility.demo1;


import com.cfhui.chain_of_responsibility.demo1.handler.LoginAuthenticationHandler;
import com.cfhui.chain_of_responsibility.demo1.handler.RequestFrequencyHandler;

//测试类
public class Demo1 {
    public static void main(String[] args) {
        //设置request对象的属性
        Request request = new Request.RequestBuilder().setRequestFrequency(true).setLoginAuthentication(true).build();
        //将登陆认证执行放置到请求频率执行器之后
        RequestFrequencyHandler requestFrequencyHandler = new RequestFrequencyHandler(new LoginAuthenticationHandler(null));
        //开始执行
        requestFrequencyHandler.process(request);
    }
}
