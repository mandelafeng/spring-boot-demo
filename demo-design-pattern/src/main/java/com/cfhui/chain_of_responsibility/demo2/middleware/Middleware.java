package com.cfhui.chain_of_responsibility.demo2.middleware;

/**
 * 中间件类.
 */
public abstract class Middleware {
    private Middleware next;

    /**
     * 构建中间件对象链
     */
    public static Middleware link(Middleware first, Middleware... chain) {
        Middleware head = first;
        for (Middleware nextInChain: chain) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    /**
     * 子类将通过具体检查来实现此方法
     */
    public abstract boolean check(String email, String password);

    /**
     * 运行检查链中的下一个对象，或者如果我们在链中的最后一个对象中，则结束遍历。
     */
    protected boolean checkNext(String email, String password) {
        if (next == null) {
            return true;
        }
        return next.check(email, password);
    }
}
