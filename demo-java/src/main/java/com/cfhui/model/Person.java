package com.cfhui.model;

import lombok.Data;

import java.io.Serializable;

/**
 * [浅拷贝]
 *
 * @author cfhui
 * @version V1
 * @date 2023/5/22 上午 10:18
 */
@Data
public class Person implements Cloneable, Serializable {
    private String name;
    private Integer age;
    private String email;
    private String desc;
    private Address address;

    @Override
    public Person clone() {
        try {
            Person clone = (Person) super.clone();
            // TODO: 复制此处的可变状态，这样此克隆就不能更改初始克隆的内部项
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
