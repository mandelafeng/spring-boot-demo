package com.chunfenghui.demospringsecurity.dao;

import com.chunfenghui.demospringsecurity.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Mapper注解,可带可不带,因为有MapperScan扫描.
 */
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users WHERE username=#{username}")
    User findByUserName(@Param("username") String username);

}
