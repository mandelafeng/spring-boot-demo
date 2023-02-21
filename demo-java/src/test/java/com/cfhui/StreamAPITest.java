package com.cfhui;

import com.cfhui.model.User;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *@ClassName StreamAPITest
 *@Description TODO
 *@Author cfhui
 *@Date 2022/12/10 18:31
 */

public class StreamAPITest {
    List<User> list = Lists.newArrayList();
    @Before
    public void test() {

        for (int i = 0; i < 10; i++) {
            User user = User.builder().id(i).userName("name" + i).age(i).sex("女").build();
            list.add(user);
        }
    }

    @Test
    public void filterTest() {
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> collect = list.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void distinctTest() {
        List<Integer> list = Arrays.asList(1, 1, 3, 4, 4, 6, 8, 8, 9);
        List<Integer> collect = list.stream().distinct().collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void mapTest() {
        List<String> collect = list.stream().map(User::getUserName).collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void limitTest() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> limit = integers.stream().limit(3).collect(Collectors.toList());
        System.out.println(limit);
    }

    @Test
    public void toMapTest() {
        Map<Integer, String> map = list.stream().collect(Collectors.toMap(User::getId, User::getUserName));
        System.out.println(map);
    }

    @Test
    public void joinTest() {
        String collect = list.stream().map(User::getSex).collect(Collectors.joining(","));
        System.out.println(collect);

    }

    @Test
    public void flatMapTest() {
//        list.stream().flatMap();
    }
}

