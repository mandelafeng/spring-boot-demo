package com.cfhui.service;

import com.cfhui.entity.UserEntity;

import java.util.List;

/**
 * @author cfhui
 * @version V1
 * @description TODO
 * @date 2023/3/16 下午 7:14
 */
public interface IUserService {

    UserEntity findById(Integer id);

    List<UserEntity> findAll();

    UserEntity save(UserEntity user);

    void delete(Integer id);

}
