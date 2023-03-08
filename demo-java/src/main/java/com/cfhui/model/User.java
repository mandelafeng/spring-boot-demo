package com.cfhui.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private Integer id;
    private String userName;
    private Integer age;
    private String sex;
    private Order order;
}
