package com.cfhui.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cfhui.entity.User;
import com.cfhui.mapper.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * [mock 测试]
 * @author cfhui
 * @since V1
 * @date 2023/3/13 下午 3:30
 */
@SpringBootTest
class UserServiceImplTest {
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("测试根据用户名获取用户信息")
    void getUser() {
        User user = new User();
        user.setId(1L);
        user.setUserName("zhang3");
        Mockito.when(userMapper.selectUserByUserName(user.getUserName())).thenReturn(user);
        User serviceUser = userService.getUserByName("zhang3");
        Assertions.assertNotNull(serviceUser);
        Assertions.assertEquals("zhang3", serviceUser.getUserName());
    }

    @Test
    @DisplayName("保存用户")
    void saveUser() {
        User user = new User();
        user.setId(2L);
        user.setUserName("li4");
        when(userMapper.insert(any(User.class))).thenReturn(1);
        boolean result = userService.saveUser(user);
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("保存用户信息")
    void update() {
        User user = new User();
        user.setUserName("li4");
        user.setEmail("123.163.com");
        when(userMapper.update(Mockito.isNull(), Mockito.any(UpdateWrapper.class))).thenReturn(1);
        boolean result = userService.update(user);
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("获取所有有效的用户列表")
    void list() {
        when(userMapper.selectList(any(QueryWrapper.class))).thenReturn(Mockito.isNull());
        List<User> list = userService.list();
        Assertions.assertNotNull(list);
    }
}
