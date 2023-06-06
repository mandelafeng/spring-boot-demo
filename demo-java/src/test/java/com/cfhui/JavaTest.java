package com.cfhui;

import com.cfhui.model.Address;
import com.cfhui.model.Person;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName JavaTest
 * @Description TODO
 * @Author cfhui
 * @Date 2023/4/5 11:32
 */
public class JavaTest {
    @Test
    public void test1() {
        int x = 2;
        change(x);
        System.out.println(x);
    }

    private void change(int x) {
        x = 100;
    }

    @Test
    public void test2() {
        Integer integer = 1;
        List<String> strings = new ArrayList<>();
    }

    @Test
    @DisplayName("浅拷贝")
    public void test3() {
        Person person = new Person();
        person.setName("hui");
        Address address = new Address();
        address.setCity("beijing");
        person.setAddress(address);

        Person clone = person.clone();
        // 两个 person 对象共用一个 address
        clone.getAddress().setCountry("china");
        System.out.println(clone);
        System.out.println(person);
    }

    @Test
    @DisplayName("深拷贝-SerializationUtils")
    public void test4() {
        Person person = new Person();
        person.setName("hui");
        Address address = new Address();
        address.setCity("beijing");
        person.setAddress(address);
        // Apache Commons Lang 需要对象实现序列化接口
        Person clone = SerializationUtils.clone(person);
        clone.getAddress().setCountry("china");
        System.out.println(person);
        System.out.println(clone);
    }

    @Test
    @DisplayName("深拷贝-")
    public void test5() {
        Person person = new Person();
        person.setName("hui");
        Address address = new Address();
        address.setCity("beijing");
        person.setAddress(address);
        // Apache Commons Lang 需要对象实现序列化接口
    }
}
