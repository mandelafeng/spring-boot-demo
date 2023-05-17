package com.cfhui;

import com.cfhui.model.User;
import org.junit.Test;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/5/11 下午 5:28
 */
public class OptionalTest {

    @Test
    public void test1() throws Throwable {
        Optional.ofNullable(null).orElseThrow((Supplier<Throwable>) () -> new RuntimeException("null"));
    }

    @Test
    public void test2() {
        User hui = User.builder().userName("hui").build();
        User user = Optional.ofNullable(hui).orElse(User.builder().userName("cf").build());
        System.out.println(user);
        System.out.println("----------");
        hui = null;
        User user2 = Optional.ofNullable(hui).orElse(User.builder().userName("cf").build());
        System.out.println(user2);
    }
}
