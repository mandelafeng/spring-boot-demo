package com.cfhui.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @author cfhui
 * @version V1
 * @description TODO
 * @date 2023/3/13 下午 3:35
 */
@Setter
@Getter
@TableName("orm_role")
public class Role {
    private int id;
    @NotBlank(message = "角色名称不能为空")
    private String name;
}
