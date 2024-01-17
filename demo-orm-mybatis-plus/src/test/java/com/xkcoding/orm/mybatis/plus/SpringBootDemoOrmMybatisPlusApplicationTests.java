package com.xkcoding.orm.mybatis.plus;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xkcoding.orm.mybatis.plus.entity.Role;
import com.xkcoding.orm.mybatis.plus.entity.User;
import com.xkcoding.orm.mybatis.plus.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoOrmMybatisPlusApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private UserMapper userMapper;

    @Test
    public void updateByIdTest() {
        User user = new User();
        user.setId(2L);
        user.setEmail("1234");
        userMapper.updateById(user);
        System.out.println(user);
    }
    @Test
    public void update() {
        User user = new User();
        user.setEmail("12345");
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", 2L);
        userMapper.update(user, wrapper);
        System.out.println(user);
    }

    @Test
    public void modelTest() {
        Role role = new Role();
        role.setName("test12_3");
        role.insert();

    }

    @Test
    public void test() {


    }

}
