package com.cfhui.service.impl;

import com.cfhui.entity.UserEntity;
import com.cfhui.repository.UserEntityRepository;
import com.cfhui.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cfhui
 * @version V1
 * @description TODO
 * @date 2023/3/16 下午 7:14
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserEntityRepository repository;

    @Override
    public UserEntity findById(Integer id) {
        return repository.getOne(id);
    }

    @Override
    public List<UserEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public UserEntity save(UserEntity user) {
        return repository.save(user);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
