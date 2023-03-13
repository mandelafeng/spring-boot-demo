package com.cfhui.controller;

import com.cfhui.entity.Role;
import com.cfhui.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cfhui
 * @version V1
 * @description TODO
 * @date 2023/3/13 下午 3:56
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @PostMapping
    public Role save(@Validated @RequestBody Role role) {
        roleService.save(role);
        return role;
    }
}
