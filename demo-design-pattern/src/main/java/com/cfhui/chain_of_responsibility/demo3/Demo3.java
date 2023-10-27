package com.cfhui.chain_of_responsibility.demo3;

import com.cfhui.chain_of_responsibility.demo3.check.PunctuationSymbolHandle;
import com.cfhui.chain_of_responsibility.demo3.check.SensitiveWordsHandle;
import com.cfhui.chain_of_responsibility.demo3.check.StringCheckHandle;
import com.cfhui.chain_of_responsibility.demo3.check.WrongWordsHandle;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/10/26 下午 3:08
 */
public class Demo3 {
    public static void main(String[] args) {
        StringCheckHandle link = StringCheckHandle.link(
            new SensitiveWordsHandle(),
            new PunctuationSymbolHandle(),
            new WrongWordsHandle()
        );
        link.check("1");

    }
}
