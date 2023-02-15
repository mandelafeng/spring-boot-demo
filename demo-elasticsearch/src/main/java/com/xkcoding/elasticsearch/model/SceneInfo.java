package com.xkcoding.elasticsearch.model;

import lombok.Data;

/**
 * @author cfhui
 * @version V1
 * @description TODO
 * @date 2023/2/7 下午 8:33
 */
@Data
public class SceneInfo {
    private Long id;
    private String sceneName;
    private String tag;
    private String sceneDesc;
    private String supplier;
    private String updateTime;
}
