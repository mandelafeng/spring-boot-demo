package com.cfhui.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cfhui.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {

    User selectUserByUserName(String userName);
}
