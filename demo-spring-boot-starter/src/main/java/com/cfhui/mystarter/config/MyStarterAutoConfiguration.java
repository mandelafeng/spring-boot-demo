package com.cfhui.mystarter.config;

import com.cfhui.mystarter.model.Person;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/5/17 上午 10:08
 */
@Configuration
@EnableConfigurationProperties(PersonConfigProperties.class)
@ConditionalOnClass(Person.class)
public class MyStarterAutoConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "demo.config", name = "enable",havingValue = "true")
    public Person defaultPerson(PersonConfigProperties properties) {
        Person person = new Person();
        person.setName(properties.getName());
        person.setAge(properties.getAge());
        return person;
    }
}
