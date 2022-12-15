package com.cfhui.model.entity;

import com.cfhui.annotation.EnumString;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author cfhui
 * @version V1
 * @description TODO
 * @date 2022/12/13 10:10
 */
@Data
public class User {
    private Integer id;
    @NotBlank(message = "名字为必填项")
    @Length(min = 2, max = 50, message = "用户名长度在2-50")
    private String userName;
    @Range(min = 0, max = 100, message = "年龄范围0-100岁。")
    private Integer age;
    @EnumString(value = {"F", "M"}, message = "性别只允许F或M")
    private String sex;
    @Email(message = "请输入正确的邮箱地址.")
    private String email;
}
