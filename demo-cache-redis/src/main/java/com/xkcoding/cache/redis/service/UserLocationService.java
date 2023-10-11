package com.xkcoding.cache.redis.service;

import com.xkcoding.cache.redis.entity.UserLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserLocationService {

    String key = "user_locations";

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void addUserLocation(UserLocation location) {
        redisTemplate.opsForGeo().add(key, new RedisGeoCommands.GeoLocation<>(location.getUserId(), new Point(location.getLongitude(), location.getLatitude())));
    }

    public GeoResults<RedisGeoCommands.GeoLocation<String>> getNearbyUsers(UserLocation location, double radius) {
        Distance distance = new Distance(radius, RedisGeoCommands.DistanceUnit.METERS);
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().sortAscending().limit(10);
        return redisTemplate.opsForGeo().radius(key, new Circle(new Point(location.getLongitude(), location.getLatitude()), distance), args);
    }
}
