package com.cfhui.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class FileChunk implements Serializable {

    private static final long serialVersionUID = 3907706848251401314L;
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("chunk_number")
    private Integer chunkNumber;
    @TableField("chunk_size")
    private Float chunkSize;
    @TableField("current_chunk_size")
    private Float currentChunkSize;
    @TableField("total_chunks")
    private Integer totalChunks;

    private String identifier;
    @TableField("file_name")
    private String fileName;
    @TableField("file_type")
    private String fileType;
    @TableField("relative_path")
    private String relativePath;

    private Integer version;

    private Integer status;
    @TableField("add_time")
    private LocalDateTime addTime;
    @TableField("update_time")
    private LocalDateTime updateTime;
}
