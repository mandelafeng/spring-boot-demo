package com.cfhui.service.impl;

import com.cfhui.entity.Role;
import com.cfhui.mapper.RoleMapper;
import com.cfhui.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cfhui
 * @version V1
 * @description TODO
 * @date 2023/3/13 下午 3:37
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Role save(Role role) {
        roleMapper.insert(role);
        return role;
    }
}
