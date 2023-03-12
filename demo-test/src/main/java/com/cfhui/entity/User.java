package com.cfhui.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 用户实体类
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-11-08 16:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("users")
public class User implements Serializable {
    private static final long serialVersionUID = -1840831686851699943L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    @TableField("username")
    private String userName;

    /**
     * 加密后的密码
     */
    private String password;

    /**
     * 加密使用的盐
     */
    private String enable;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String roles;

}
