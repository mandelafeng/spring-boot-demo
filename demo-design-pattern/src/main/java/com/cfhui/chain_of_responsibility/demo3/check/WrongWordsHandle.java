package com.cfhui.chain_of_responsibility.demo3.check;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/10/26 下午 3:06
 */
public class WrongWordsHandle extends StringCheckHandle{
    @Override
    public boolean check(String str) {
        System.out.println("错别字检查");
        return checkNext(str);
    }
}
