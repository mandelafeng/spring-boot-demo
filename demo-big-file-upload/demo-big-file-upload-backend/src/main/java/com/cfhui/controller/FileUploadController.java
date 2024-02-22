package com.cfhui.controller;

import com.cfhui.dto.FileChunkDTO;
import com.cfhui.dto.ResponseResult;
import com.cfhui.dto.MessageEnum;
import com.cfhui.param.FileChunkParam;
import com.cfhui.service.IFileChunkService;
import com.cfhui.service.IFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class FileUploadController {

    private final IFileService fileService;

    private final IFileChunkService fileChunkService;

    @Autowired
    public FileUploadController(IFileService fileService, IFileChunkService fileChunkService) {
        this.fileService = fileService;
        this.fileChunkService = fileChunkService;
    }

    @GetMapping("/upload")
    public ResponseResult<Map<String, Object>> checkUpload(@Validated FileChunkParam param) {
        log.info("文件MD5:" + param.getIdentifier());
        List<FileChunkDTO> list = fileChunkService.listByFileMd5(param.getIdentifier());
        Map<String, Object> data = new HashMap<>(1);
        if (list.isEmpty()) {
            data.put("uploaded", false);
            return ResponseResult.ok(data);
        }
        // 处理单文件
        if (list.get(0).getTotalChunks() == 1) {
            data.put("uploaded", true);
            // todo 返回 url
            data.put("url", "");
            return ResponseResult.ok(data);
        }
        // 处理分片
        int[] uploadedFiles = new int[list.size()];
        int index = 0;
        for (FileChunkDTO fileChunkItem : list) {
            uploadedFiles[index] = fileChunkItem.getChunkNumber();
            index++;
        }
        data.put("uploadedChunks", uploadedFiles);
        return ResponseResult.ok(data);
    }

    @PostMapping("/upload")
    public ResponseResult<String> chunkUpload(@Validated FileChunkParam param) {
        return fileService.uploadFile(param) ? ResponseResult.ok() : ResponseResult.error(MessageEnum.FAIL);
    }

}
