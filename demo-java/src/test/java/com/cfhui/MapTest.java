package com.cfhui;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * @author cfhui
 * @version V1
 * @description TODO
 * @date 2023/2/21 下午 1:20
 */
public class MapTest {
    private Map<String, Integer> map;

    @Before
    public void initData() {
        map = new HashMap<>();
        map.put("香蕉", 5);
        map.put("苹果", 8);
    }
    /**
     * [forEach遍历map]
     * 注：不知道lambda如何写，ctrl+p看参数提示，先new一个出来，然后根据idea提示替换掉
     * @author cfhui
     * @since V1
     * @date 2023/2/21 下午 1:31
     */
    @Test
    public void test1() {
        map.forEach((good, price) -> {
            System.out.println(good + ": " + price);
        });
    }
    /**
     * computeIfAbsent 如果key不存在，执行lambda内的，返回生成的值
     *
     * @author cfhui
     * @since V1
     * @date 2023/2/21 下午 1:39
     */
    @Test
    public void test2() {
        Integer yaLi = map.computeIfAbsent("鸭梨", s -> 10);
        Assert.assertSame(10, yaLi);
    }

    /**
     * computeIfPresent 如果key存在，执行lambda内的，返回生成的值,不存在返回null
     *
     * @author cfhui
     * @since V1
     * @date 2023/2/21 下午 1:39
     */
    @Test
    public void test3() {
        map.computeIfPresent("香蕉", new BiFunction<String, Integer, Integer>() {
            @Override
            public Integer apply(String s, Integer integer) {
                return null;
            }
        });
    }

    /**
     * compute 不关心存不存在值，执行lambda的内容放入
     *
     * @author cfhui
     * @since V1
     * @date 2023/2/21 下午 1:39
     */
    @Test
    public void test4() {
        map.compute("", new BiFunction<String, Integer, Integer>() {
            @Override
            public Integer apply(String s, Integer integer) {
                return null;
            }
        });
    }

    /**
     * merge
     *
     * @author cfhui
     * @since V1
     * @date 2023/2/21 下午 1:39
     */
    @Test
    public void test5() {
        map.merge("", 0, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return null;
            }
        });
    }

    /**
     * putIfAbsent 和 put的区别
     * put：如果存在，保留最新put的数据
     * outIfAbsent: 如果存在，保留最早的数据
     *
     * @author cfhui
     * @since V1
     * @date 2023/2/21 下午 1:39
     */
    @Test
    public void test6() {
        map.putIfAbsent("", 0);
    }

    @Test
    public void test7() {
        Multimap<Integer, Integer> waitingMap = ArrayListMultimap.create();
        waitingMap.put(1, 1);
        waitingMap.put(1, 2);
        Map<Integer, Collection<Integer>> map = waitingMap.asMap();
        Collection<Integer> collection = map.get(1);
        collection.forEach(System.out::println);
    }

    @Test
    public void test8() {
        Map<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.getOrDefault(1, 0);
    }
}
