package com.cfhui.controller;

import com.cfhui.constant.codeConst.LoginResponseCodeConst;
import com.cfhui.model.ResponseDTO;
import com.cfhui.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cfhui
 * @version V1
 * @description TODO
 * @date 2022/12/28 14:27
 */
@RestController
@RequestMapping("")
public class CommonResponse {

    @GetMapping("/test1")
    public ResponseDTO<User> test1() {
        return ResponseDTO.success();
    }
    @GetMapping("/test2")
    public ResponseDTO<User> test2() {
        User user = new User();
        user.setName("hui");
        user.setAge(20);
        return ResponseDTO.success(user);
    }
    @GetMapping("/test3")
    public ResponseDTO<User> test3() {
        User user = new User();
        user.setName("hui");
        user.setAge(20);
        return ResponseDTO.success(user,"请求用户数据成功！");
    }
    @GetMapping("/test4")
    public ResponseDTO<User> test4() {
        return ResponseDTO.wrap(LoginResponseCodeConst.LOGIN_ERROR);
    }
    @GetMapping("/test5")
    public ResponseDTO<User> test5() {
        return ResponseDTO.wrap(LoginResponseCodeConst.LOGIN_ERROR, "登录错误");
    }

}
