package com.cfhui.service;

import com.cfhui.param.FileChunkParam;

public interface IFileService {

    /**
     * 上传文件
     * @param param 参数
     * @return
     */
    boolean uploadFile(FileChunkParam param);
}
