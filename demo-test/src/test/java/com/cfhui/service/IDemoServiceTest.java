package com.cfhui.service;

import com.cfhui.entity.User;
import com.cfhui.mapper.UserMapper;
import com.cfhui.service.impl.DemoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IDemoServiceTest {

    @Mock
    private UserMapper userMapper;



    @InjectMocks
    private DemoServiceImpl demoService;

    @Test
    @DisplayName("测试根据用户名获取用户信息")
    void getUser() {
        User user = new User();
        user.setId(1L);
        user.setUserName("zhang3");
        Mockito.when(userMapper.selectUserByUserName(user.getUserName())).thenReturn(user);
        User serviceUser = demoService.getUserByName("zhang3");
        Assertions.assertNotNull(serviceUser);
        Assertions.assertEquals("zhang3", serviceUser.getUserName());
    }
}
