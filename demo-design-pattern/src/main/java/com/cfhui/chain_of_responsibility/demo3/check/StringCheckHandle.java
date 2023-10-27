package com.cfhui.chain_of_responsibility.demo3.check;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/10/26 下午 2:47
 */
public abstract class StringCheckHandle {
    private StringCheckHandle next;

    public abstract boolean check(String str);

    public static StringCheckHandle link(StringCheckHandle first, StringCheckHandle... chain) {
        StringCheckHandle head = first;
        for (StringCheckHandle handle : chain) {
            head.next = handle;
            head = handle;
        }
        return first;
    }

    protected boolean checkNext(String str) {
        if (next == null) {
            return true;
        }
        return next.check(str);
    }
}
