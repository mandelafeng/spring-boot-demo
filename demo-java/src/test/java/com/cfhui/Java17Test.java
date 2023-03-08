package com.cfhui;

import com.cfhui.model.Apple;
import com.cfhui.model.Fruit;
import com.cfhui.model.User;
import com.cfhui.model.UserRecord;
import org.junit.Test;

import java.text.NumberFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

/**
 * @author cfhui
 * @version V1
 * @description TODO
 * @date 2023/3/8 下午 4:08
 */
public class Java17Test {
    /**
     * [文本块]
     *
     * @return void
     * @author cfhui
     * @date 2023/3/8 下午 4:20
     * @since V1
     */
    @Test
    public void test1() {
        String res = """
            {
                "name": "小白",
                "age": 20
            }
            """;
        System.out.println(res);
    }

    /**
     * [switch 将冒号:替换为-> 所以可以不使用break]
     * [也可以有返回值]
     *
     * @author cfhui
     * @date 2023/3/8 下午 4:30
     * @since V1
     */
    @Test
    public void test2() {
        String fruit = "apple";
        String res = switch (fruit) {
            case "apple" -> "普通水果";
            case "mango" -> "进口水果";
            default -> "未知水果";
        };
        System.out.println(res);
    }

    /**
     * [可以使用{}包含多条处理语句，返回使用关键字 yield]
     *
     * @return void
     * @author cfhui
     * @date 2023/3/8 下午 4:38
     * @since V1
     */
    @Test
    public void test3() {
        String fruit = "apple";
        String res = switch (fruit) {
            case "apple" -> {
                System.out.println("普通水果");
                yield "普通水果return";
            }
            case "mango" -> {
                System.out.println("进口水果");
                yield "进口水果return";
            }
            default -> {
                System.out.println("未知水果");
                yield "未知水果return";
            }

        };
        System.out.println(res);
    }

    /**
     * [record 挑选需要的属性而不用再建一个类似的类]
     *
     * @return void
     * @author cfhui
     * @date 2023/3/8 下午 4:57
     * @since V1
     */
    @Test
    public void test4() {
//        User user = User.builder().id(12).userName("小白").age(20).sex("男").build();
        record UserRecord(String userName, int age) {
        }

        UserRecord hong = new UserRecord("小红", 18);
        UserRecord huang = new UserRecord("小黄", 17);
        System.out.println(hong);
        System.out.println(huang);
    }

    /**
     * [record 也可作为一个文件存在]
     *
     * @return void
     * @author cfhui
     * @date 2023/3/8 下午 5:02
     * @since V1
     */
    @Test
    public void test5() {
//        User user = User.builder().id(12).userName("小白").age(20).sex("男").build();
        UserRecord lan = new UserRecord("小兰", 16);
        UserRecord zi = new UserRecord("小紫", 15);
        System.out.println(lan);
        System.out.println(zi);
    }

    /**
     * [java17-NPE会打印具体的方法]
     *
     * @return void
     * @author cfhui
     * @date 2023/3/8 下午 5:16
     * @since V1
     */
    @Test
    public void test6() {
//        java.lang.NullPointerException: Cannot invoke "com.cfhui.model.Order.getOrderId()" because the return value of "com.cfhui.model.User.getOrder()" is null
        User user = User.builder().id(12).userName("小白").age(20).sex("男").build();
        System.out.println(user.getOrder().getOrderId());
    }

    /**
     * 在Java 17中添加了一个新的模式B,用于格式化DateTime,它根据Unicode标准指示一天时间段。
     * 自动的语言环境 中文输出中文，英文输出英文
     *
     * @return void
     * @author cfhui
     * @date 2023/3/8 下午 5:27
     * @since V1
     */
    @Test
    public void test7() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("B");
        System.out.println(dtf.format(LocalTime.of(8, 0)));
        System.out.println(dtf.format(LocalTime.of(13, 0)));
        System.out.println(dtf.format(LocalTime.of(20, 0)));
        System.out.println(dtf.format(LocalTime.of(23, 0)));
        System.out.println(dtf.format(LocalTime.of(0, 0)));
/*        上午
            下午
        晚上
            晚上
        午夜*/
    }
    /**
     * [在NumberFormat中添加了一个工厂方法，可以根据Unicode标准以紧凑的、人类可读的形式格式化数字]
     *
     * @return void
     * @author cfhui
     * @since V1
     * @date 2023/3/8 下午 5:32
     */
    @Test
    public void test8() {
        NumberFormat nft = NumberFormat.getCompactNumberInstance(Locale.ENGLISH, NumberFormat.Style.SHORT);
        System.out.println(nft.format(1000));
        System.out.println(nft.format(100000));
        System.out.println(nft.format(10000000));
/*        1 K
        100K
        10M*/
    }
    /**
     * [如果需要将Stream转换成List,需要通过调用collect方法使用Collectors.toList()，代码非常冗长。]
     * [在Java 17中将会变得简单，可以直接调用toList()。]
     *
     * @author cfhui
     * @since V1
     * @date 2023/3/8 下午 5:36
     */
    @Test
    public void test9() {
        Stream<String> a = Stream.of("a", "b", "c");
        List<String> list = a.toList();
        System.out.println("list = " + list);
    }
    /**
     * [java17-instanceof简写]
     *
     * @author cfhui
     * @since V1
     * @date 2023/3/8 下午 5:46
     */
    @Test
    public void test10() {
        Apple apple = new Apple();
        Object o = new Object();
        if (o instanceof Fruit fruit) {
            System.out.println(fruit.getName());
        }
    }
    /**
     * [java17密封类]
     * [在定义Furit时通过关键字sealed声明为密封类，通过permits可以指定Apple,Pear类可以进行继承扩展。
     *
     * 子类需要指明它是final,non-sealed或sealed的。父类不能控制子类是否可以被继承。]
     *
     * @return void
     * @author cfhui
     * @since V1
     * @date 2023/3/8 下午 5:49
     */
    @Test
    public void test11() {
        //public class Tomato extends Fruit {}
    }
}
