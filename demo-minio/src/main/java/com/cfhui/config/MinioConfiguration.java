package com.cfhui.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author cfhui
 * @version V1
 * @description minio配置
 * @date 2022/12/12 13:10
 */
@Configuration
public class MinioConfiguration {

    private MinioProperties minioProperties;

    @Autowired
    public void setMinioProperties(MinioProperties minioProperties) {
        this.minioProperties = minioProperties;
    }

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
            .endpoint(minioProperties.getEndpoint())
            .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
            .build();
    }
}
