package com.cfhui.valid;

import javax.validation.groups.Default;

/**
 * [分组校验]
 * @author cfhui
 * @version V1
 * @date 2023/3/8 上午 9:57
 */
public class ValidGroups {
    public interface UpdateUser extends Default {}

    public interface InsertUser extends Default {}
}
