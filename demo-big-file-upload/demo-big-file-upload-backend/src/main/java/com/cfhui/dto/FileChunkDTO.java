package com.cfhui.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class FileChunkDTO {

    private Long id;
    private Integer chunkNumber;
    private Float chunkSize;
    private Float currentChunkSize;
    private Integer totalChunks;
    private String identifier;
    private String fileName;
    private String fileType;
    private String relativePath;
    private LocalDateTime addTime;
}
