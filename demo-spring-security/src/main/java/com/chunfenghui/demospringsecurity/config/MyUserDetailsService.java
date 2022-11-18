package com.chunfenghui.demospringsecurity.config;

import com.chunfenghui.demospringsecurity.dao.UserMapper;
import com.chunfenghui.demospringsecurity.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("进入了...");
        User user = userMapper.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        user.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoles()));
        return user;
    }
}
