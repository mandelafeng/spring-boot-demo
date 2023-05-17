package com.cfhui.mystarter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/5/17 上午 10:06
 */
@ConfigurationProperties(prefix = "demo.config.person")
public class PersonConfigProperties {
    private String name;
    private Integer age;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}';
    }
}
