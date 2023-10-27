package com.cfhui.chain_of_responsibility.demo3.check;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/10/26 下午 2:57
 */
public class PunctuationSymbolHandle extends StringCheckHandle{
    @Override
    public boolean check(String str) {
        System.out.println("标点符号检查");
        if ("1".equals(str)) {
            return false;
        }
        return checkNext(str);
    }
}
