package com.cfhui;

import com.cfhui.model.User;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName StreamAPITest
 * @Description TODO
 * @Author cfhui
 * @Date 2022/12/10 18:31
 */

public class StreamAPITest {
    List<User> list = Lists.newArrayList();
    List<List<User>> bigList = Lists.newArrayList();

    @Before
    public void test() {

        for (int i = 0; i < 10; i++) {
            User user = User.builder().id(i).userName("name" + i).age(i).sex("女").build();
            list.add(user);
            // TODO: 2023/3/7 cfhui
            // TODO: 2023/3/7 cfhui
            // TODO: 2023/3/7 cfhui
        }
        list.add(User.builder().id(11).userName("name11").age(1).sex("女").build());
        bigList = new ArrayList<>();
        bigList.add(list);
        List<User> list1 = new ArrayList<>();
        list1.add(User.builder().id(11).userName("name12").age(1).sex("未知").build());
        bigList.add(list1);
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
    @DisplayName("flatMap")
    public void flatMapTest() {
        // map 和 flatMap的区别
        // map 对于 List<List> 分开处理
        // flatMap 先合并成一个List 再统一处理
        List<String> list1 = Arrays.asList("hello", "world");
        list1.stream().map(s -> s.split("")).flatMap(Arrays::stream).forEach(System.out::println);
        System.out.println("---------");
        Stream<List<Integer>> list2 = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4), Arrays.asList(5, 6));
        List<Integer> collect = list2.flatMap(Collection::stream).map(x -> x * x).collect(Collectors.toList());
        System.out.println("collect = " + collect);


    }


    @Test
    @DisplayName("limit")
    public void test12() {
        list.stream().sorted(Comparator.comparingInt(User::getAge)).limit(6).collect(Collectors.toList());
    }

    @Test
    @DisplayName("group by 分组")
    public void test13() {
        Map<Integer, List<User>> map = list.stream().collect(Collectors.groupingBy(User::getAge));
        map.forEach((k, v) -> {
            map.put(k, v.stream().sorted((o1, o2) -> -(o1.getId() - o2.getId())).collect(Collectors.toList()));
        });
        System.out.println(map);
    }

    @Test
    @DisplayName("reduce 归约")
    public void test14() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list.stream().reduce(Integer::sum).ifPresent(System.out::println);
    }

    @Test
    public void test15() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Map<Boolean, List<Integer>> collect = list.stream().collect(Collectors.partitioningBy(i -> i % 2 == 0));

        System.out.println(collect);
    }


}

