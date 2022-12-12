package com.cfhui.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author cfhui
 * @version V1
 * @description minio属性
 * @date 2022/12/12 13:12
 */
@Data
@Configuration
@ConfigurationProperties("minio")
public class MinioProperties {
    /**
     * 服务地址
     */
    private String endpoint;

    /**
     * 存储桶名称
     */
    private String bucket;
    /**
     * 用户名
     */
    private String accessKey;
    /**
     * 密码
     */
    private String secretKey;
}
