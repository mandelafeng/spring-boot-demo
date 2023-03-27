package com.cfhui.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cfhui.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}
