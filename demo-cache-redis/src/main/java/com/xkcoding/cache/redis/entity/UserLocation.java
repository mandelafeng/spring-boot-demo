package com.xkcoding.cache.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLocation {
    private double longitude;
    private double latitude;
    private String userId;
}
