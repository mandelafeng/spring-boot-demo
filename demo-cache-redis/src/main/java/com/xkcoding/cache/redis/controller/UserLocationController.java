package com.xkcoding.cache.redis.controller;

import com.xkcoding.cache.redis.entity.UserLocation;
import com.xkcoding.cache.redis.service.UserLocationService;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
/**
 * [ redis 附近的人  ]
 * @author cfhui
 * @since V1
 * @date 2023/10/11 下午 2:18
 */
@RestController
public class UserLocationController {

    @Resource
    private UserLocationService userLocationService;

    @PostMapping("/user/addLocation")
    public void addUserLocation(@RequestBody UserLocation userLocation) {
        userLocationService.addUserLocation(userLocation);
    }

    @PostMapping("/user/nearby")
    public GeoResults<RedisGeoCommands.GeoLocation<String>> getNearbyUsers(
            @RequestParam double longitude,
            @RequestParam double latitude,
            @RequestParam double radius) {
        UserLocation location = new UserLocation(longitude, latitude, null);
        return userLocationService.getNearbyUsers(location, radius);
    }
}
