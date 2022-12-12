package com.cfhui;

import com.cfhui.config.MinioProperties;
import com.cfhui.util.MinioUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author cfhui
 * @version V1
 * @description minio控制器
 * @date 2022/12/12 13:22
 */
@RestController
@RequestMapping("/minio")
public class MinioController {
    @Autowired
    private MinioProperties minioProperties;

    @SneakyThrows
    @PostMapping(value = "/upload")
    public String upload(@RequestParam(name = "file")MultipartFile multipartFile) {
        String filename = multipartFile.getOriginalFilename();
        MinioUtil.createBucket(minioProperties.getBucket());
        MinioUtil.uploadFile(minioProperties.getBucket(), multipartFile, filename);
        return MinioUtil.getPreSignedObjectUrl(minioProperties.getBucket(), filename);
    }
}
