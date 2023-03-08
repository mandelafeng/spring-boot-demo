package com.cfhui.model;

import lombok.Data;

/**
 * @author cfhui
 * @version V1
 * @description TODO
 * @date 2023/3/8 下午 5:41
 */
@Data
public sealed class  Fruit permits Apple{
    private String name;
}
