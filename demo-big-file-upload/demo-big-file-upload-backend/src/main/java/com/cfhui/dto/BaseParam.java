package com.cfhui.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseParam {
    private Long id;

    public boolean isNew() {
        return null == this.id;
    }
}
