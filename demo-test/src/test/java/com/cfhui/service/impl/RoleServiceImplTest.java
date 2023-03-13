package com.cfhui.service.impl;

import com.cfhui.entity.Role;
import com.cfhui.service.IRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class RoleServiceImplTest {

    @Autowired
    private IRoleService roleService;


    @Test
    void save() {
        Role role = new Role();
        role.setName("0313");
        roleService.save(role);

    }
}
