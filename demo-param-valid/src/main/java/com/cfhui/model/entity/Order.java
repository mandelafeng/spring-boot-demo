package com.cfhui.model.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author cfhui
 * @version V1
 * @description TODO
 * @date 2023/3/8 上午 9:32
 */
@Data
public class Order {
    @NotNull(message = "订单id不能为空")
    private Long orderId;
}
