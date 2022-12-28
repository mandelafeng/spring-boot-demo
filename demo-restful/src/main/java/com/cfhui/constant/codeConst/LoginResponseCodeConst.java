package com.cfhui.constant.codeConst;

import com.cfhui.constant.ResponseCodeConst;

/**
 * @description 登录响应常量类
 * @author cfhui
 * @since V1
 * @date 2022/12/28 14:25
 */
public class LoginResponseCodeConst extends ResponseCodeConst {

    public static final LoginResponseCodeConst LOGIN_ERROR = new LoginResponseCodeConst(1001, "您还未登录或登录失效，请重新登录！");

    public static final LoginResponseCodeConst NOT_HAVE_PRIVILEGES = new LoginResponseCodeConst(1002, "对不起，您没有权限哦！");

    public LoginResponseCodeConst(int code, String msg) {
        super(code, msg);
    }
}
