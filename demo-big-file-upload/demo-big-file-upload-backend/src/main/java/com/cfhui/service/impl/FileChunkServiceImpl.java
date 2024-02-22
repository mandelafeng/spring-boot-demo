package com.cfhui.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cfhui.dao.IFileChunkDao;
import com.cfhui.dto.FileChunkDTO;
import com.cfhui.dto.MessageEnum;
import com.cfhui.entity.FileChunk;
import com.cfhui.exception.BusinessException;
import com.cfhui.param.FileChunkParam;
import com.cfhui.service.IFileChunkService;
import com.cfhui.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service(value = "fileChunkService")
public class FileChunkServiceImpl implements IFileChunkService {

    private final IFileChunkDao fileChunkDao;

    @Autowired
    public FileChunkServiceImpl(IFileChunkDao fileChunkDao) {
        this.fileChunkDao = fileChunkDao;
    }

    @Override
    public List<FileChunkDTO> listByFileMd5(String md5) {
        LambdaQueryWrapper<FileChunk> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FileChunk::getIdentifier, md5);
        List<FileChunk> fileChunks = this.fileChunkDao.selectList(wrapper);
        if (fileChunks == null || fileChunks.isEmpty()) {
            return Collections.emptyList();
        }
        return BeanUtils.doListToDtoList(fileChunks, FileChunkDTO.class);
    }

    @Override
    public void saveFileChunk(FileChunkParam param) {
        FileChunk FileChunk = null;
        if (!param.isNew()) {
            FileChunk = this.fileChunkDao.selectById(param.getId());
            if (null == FileChunk) {
                throw new BusinessException(MessageEnum.RECORD_NOT_EXISTED);
            }
        }
        if (null == FileChunk) {
            FileChunk = new FileChunk();
        } else {
            FileChunk.setUpdateTime(LocalDateTime.now());
        }
        org.springframework.beans.BeanUtils.copyProperties(param, FileChunk, "id");
        FileChunk.setFileName(param.getFilename());
        int result;
        if (param.isNew()) {
            result = this.fileChunkDao.insert(FileChunk);
        } else {
            result = this.fileChunkDao.updateById(FileChunk);
        }
        if (0 == result) {
            throw new BusinessException(MessageEnum.FAIL);
        }
        //HongBeanUtils.doToDto(FileChunk, FileChunkDTO.class);
    }

    @Override
    public int deleteById(Long id) {
       FileChunk FileChunk = this.fileChunkDao.selectById(id);
       if (null == FileChunk) {
         throw new BusinessException(MessageEnum.RECORD_NOT_EXISTED);
       }
       return this.fileChunkDao.deleteById(FileChunk.getId());
    }

    @Override
    public int delete(FileChunk fileChunk) {
        return this.fileChunkDao.deleteById(fileChunk.getId());
    }
}
