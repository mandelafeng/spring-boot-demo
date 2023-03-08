package com.cfhui.model.entity;

import com.cfhui.annotation.EnumString;
import com.cfhui.valid.ValidGroups;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author cfhui
 * @version V1
 * @description TODO
 * @date 2022/12/13 10:10
 */
@Data
public class User {
    private Integer id;
    // 分组校验
    @NotBlank(message = "名字为必填项", groups = ValidGroups.UpdateUser.class)
    @Length(min = 2, max = 50, message = "用户名长度在2-50")
    private String userName;
    @Range(min = 0, max = 100, message = "年龄范围0-100岁。")
    private Integer age;
    @EnumString(value = {"F", "M"}, message = "性别只允许F或M")
    private String sex;
//    @Email(message = "请输入正确的邮箱地址.")
//    @NotBlank
//    private String email;

    // 递归校验
    @Valid
    @NotNull(message = "订单信息不能全为空")
    private Order order;
}
