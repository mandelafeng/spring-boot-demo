package com.cfhui.controller;

import com.cfhui.model.entity.ResponseBean;
import com.cfhui.model.entity.User;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cfhui
 * @version V1
 * @description TODO
 * @date 2022/12/13 10:12
 */
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
    @GetMapping(value = "/user")
    public ResponseBean test(@Validated User user) {
        return ResponseBean.success("ok", user);
    }
}
