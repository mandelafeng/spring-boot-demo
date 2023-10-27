package com.cfhui.chain_of_responsibility.demo2.middleware;

/**
 * ConcreteHandler. 检查失败的登录请求是否过多
 */
public class ThrottlingMiddleware extends Middleware {
    private int requestPerMinute;
    private int request;
    private long currentTime;

    public ThrottlingMiddleware(int requestPerMinute) {
        this.requestPerMinute = requestPerMinute;
        this.currentTime = System.currentTimeMillis();
    }

    /**
     请不要在此方法的开头和结尾插入 checkNext（） 调用。这比对所有中间件对象的简单循环提供了更大的灵活性。
     例如，链中的某个元素可以通过在所有其他检查之后运行其检查来更改检查顺序。
     */
    @Override
    public boolean check(String email, String password) {
        if (System.currentTimeMillis() > currentTime + 60_000) {
            request = 0;
            currentTime = System.currentTimeMillis();
        }

        request++;

        if (request > requestPerMinute) {
            System.out.println("Request limit exceeded!");
            Thread.currentThread().stop();
        }
        return checkNext(email, password);
    }
}
