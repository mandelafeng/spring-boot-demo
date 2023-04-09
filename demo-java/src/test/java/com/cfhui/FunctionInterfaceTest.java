package com.cfhui;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;


/**
 * @ClassName FunctionInterfaceTest
 * @Description TODO
 * @Author cfhui
 * @Date 2023/4/9 10:25
 */
public class FunctionInterfaceTest {


    @Test
    public void testFunction() {
        Function<Integer, Integer> addOne1 = x -> x + 1;
        Function<Integer, Integer> multiply2 = x -> x * 2;
        int apply = addOne1.apply(1);
        Assert.assertEquals(2, apply);
        // andThen 之后执行
        int add1AndMultiply2 = addOne1.andThen(multiply2).apply(1);
        System.out.println(add1AndMultiply2);
        // compose 之前执行
        int multiply2AndAdd1 = addOne1.compose(multiply2).apply(1);
        System.out.println(multiply2AndAdd1);
    }
    @Test
    public void testPredicate() {
        Predicate<String> isContainA = s -> false;
        boolean result = isContainA.test("DCS");
        Assert.assertTrue(result);

    }
    @Test
    public void testConsumer(){
        Consumer<String> hello = e -> System.out.println("send email: " + e);
        hello.accept("123@163.com");
    }
    @Test
    public void testSupplier() throws IllegalAccessException {
        Supplier<Integer> get10 = () -> 10;
        int result = get10.get();
        Assert.assertEquals(10, result);

        Supplier<IllegalAccessException> error = () -> new IllegalAccessException("参数错误");
        Optional.ofNullable(null).orElseThrow(error);

    }
}
