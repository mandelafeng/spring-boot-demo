package com.cfhui.service.impl;

import com.cfhui.entity.UserEntity;
import com.cfhui.repository.UserEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
//@Transactional
class UserServiceImplTest {

    @Autowired
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserEntityRepository repository;

    @Test
    void findById() {
        UserEntity user = userService.findById(1);
        log.info(user.toString());
        Assertions.assertNotNull(user);
    }

    @Test
    void findAll() {
        List<UserEntity> all = userService.findAll();
        log.info(all.toString());
        Assertions.assertNotNull(all);
    }

    @Test
    void save() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(3);
        userEntity.setName("hui0316");
        UserEntity save = userService.save(userEntity);
        log.info(save.toString());
        Assertions.assertNotNull(save);
    }

    @Test
    void delete() {
        // TODO: 2023/3/17 void 如何单元测试
        // 验证方法调用次数
        Mockito.verify(userService, Mockito.times(1)).delete(2);
    }
}
