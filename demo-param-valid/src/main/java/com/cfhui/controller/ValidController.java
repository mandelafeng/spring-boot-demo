package com.cfhui.controller;

import com.cfhui.model.entity.ResponseBean;
import com.cfhui.model.entity.User;
import com.cfhui.valid.ValidGroups;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author cfhui
 * @version V1
 * @description TODO
 * @date 2022/12/13 10:12
 */
@Slf4j
@RestController
@RequestMapping("/valid")
public class ValidController {
    /**
     * @description 单参数使用需要在类上加 @Validated
     * @param age
     * @return com.cfhui.model.entity.User
     * @author cfhui
     * @since V1
     * @date 2022/12/13 14:19
     */
    @GetMapping
    public User singleParamValid( @Range(min = 0, max = 100, message = "年龄范围在0-100之间") Integer age) {
        // singleParamValid.age: 年龄范围在0-100之间
        // 上面的是返回的消息，多出了 singleParamValid.age: ，需要设置全局异常处理过滤掉
        User user = new User();
        user.setAge(age);
        return user;
    }
    /**
     * [模拟用户注册，不需要用户名-分组]
     * @param user
     * @return com.cfhui.model.entity.ResponseBean
     * @author cfhui
     * @since V1
     * @date 2023/3/8 上午 9:13
     */
    @PostMapping(value = "/user")
    public ResponseBean test1(@RequestBody @Validated User user) {
        log.info(user.toString());
        return ResponseBean.success("ok", user);
    }
    /**
     * [模拟用户更新，需要用户名-分组]
     * @param user
     * @return com.cfhui.model.entity.ResponseBean
     * @author cfhui
     * @since V1
     * @date 2023/3/8 上午 9:13
     */
    @PutMapping(value = "/user")
    public ResponseBean test2(@RequestBody @Validated({ValidGroups.UpdateUser.class}) User user) {
        log.info(user.toString());
        return ResponseBean.success("ok", user);
    }
}
