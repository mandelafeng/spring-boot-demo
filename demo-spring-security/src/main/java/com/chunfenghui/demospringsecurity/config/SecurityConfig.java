package com.chunfenghui.demospringsecurity.config;

import com.chunfenghui.demospringsecurity.Handler.SecurityAuthLimitHandler;
import com.chunfenghui.demospringsecurity.Handler.SecurityAuthenticationEntryPoint;
import com.chunfenghui.demospringsecurity.Handler.SecurityAuthenticationFailureHandler;
import com.chunfenghui.demospringsecurity.Handler.SecurityAuthenticationSuccesHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**")
            .antMatchers("/css/**")
            .antMatchers("/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 开启跨域
        http.cors();
        http.csrf().disable();
        // 授权认证
        http.authorizeRequests().anyRequest().authenticated();
        // 登录配置
        http.formLogin().usernameParameter("username").passwordParameter("password").loginProcessingUrl("/login");
        // 登录成功后的处理
        http.formLogin().successHandler(new SecurityAuthenticationSuccesHandler());
        // 登录失败后的处理
        http.formLogin().failureHandler(new SecurityAuthenticationFailureHandler());
        // 登录超时或者未登录处理
        http.exceptionHandling().authenticationEntryPoint(new SecurityAuthenticationEntryPoint());
        // 权限不足处理器
        http.exceptionHandling().accessDeniedHandler(new SecurityAuthLimitHandler());




    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
