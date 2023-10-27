package com.cfhui.chain_of_responsibility.demo3.check;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/10/26 下午 3:03
 */
public class SensitiveWordsHandle extends StringCheckHandle{
    @Override
    public boolean check(String str) {
        System.out.println("敏感词检查");
        return checkNext(str);
    }
}
