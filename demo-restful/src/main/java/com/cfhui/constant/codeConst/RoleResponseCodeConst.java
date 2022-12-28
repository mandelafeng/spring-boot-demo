package com.cfhui.constant.codeConst;

import com.cfhui.constant.ResponseCodeConst;

public class RoleResponseCodeConst extends ResponseCodeConst {

    /**
     * 10501 角色名称已存在
     */
    public static final RoleResponseCodeConst ROLE_NAME_EXISTS = new RoleResponseCodeConst(6001, "角色名称已存在");

    /**
     * 10502 角色不存在
     */
    public static final RoleResponseCodeConst ROLE_NOT_EXISTS = new RoleResponseCodeConst(6002, "角色不存在");

    public RoleResponseCodeConst(int code, String msg) {
        super(code, msg);
    }
}
