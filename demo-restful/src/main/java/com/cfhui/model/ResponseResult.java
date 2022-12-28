package com.cfhui.model;

import com.cfhui.constant.ResponseCodeConst;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

/**
 * 操作消息提醒
 */
public class ResponseResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";

    /**
     * 返回内容
     */
    public static final String MSG_TAG = "msg";

    /**
     * 数据对象
     */
    public static final String DATA_TAG = "data";

    public static final String FLAG_TAG = "flag";

    /**
     * 初始化一个新创建的 AjaxResult 对象，使其表示一个空消息。
     */
    public ResponseResult() {
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     */
    public ResponseResult(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    public ResponseResult(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (data != null) {
            super.put(DATA_TAG, data);
        }
    }

    public  Object getData(){
        return super.get(DATA_TAG);
    }
    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static ResponseResult success() {
        return ResponseResult.success("操作成功");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static ResponseResult success(Object data) {
        return ResponseResult.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static ResponseResult success(String msg) {
        return ResponseResult.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static ResponseResult success(String msg, Object data) {
        ResponseResult responseResult = new ResponseResult(HttpStatus.OK.value(), msg, data);
        responseResult.put(FLAG_TAG,true);
        return responseResult;
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static ResponseResult error() {
        return ResponseResult.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static ResponseResult error(String msg) {
        return ResponseResult.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static ResponseResult error(String msg, Object data) {
        return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 警告消息
     */
    public static ResponseResult error(int code, String msg) {
        return new ResponseResult(code, msg, null);
    }

    /**
     * 方便链式调用
     *
     * @param key   键
     * @param value 值
     * @return 数据对象
     */
    @Override
    public ResponseResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public ResponseResult(ResponseCodeConst codeConst) {
        super.put(CODE_TAG, codeConst.getCode());
        super.put(MSG_TAG, codeConst.getMsg());
    }

    public ResponseResult(ResponseCodeConst codeConst, Object data) {
        super.put(CODE_TAG, codeConst.getCode());
        super.put(MSG_TAG, codeConst.getMsg());
        super.put(DATA_TAG, data);
    }
    public ResponseResult(ResponseCodeConst codeConst, String msg, Object data) {
        super.put(CODE_TAG, codeConst.getCode());
        super.put(MSG_TAG, msg);
        super.put(DATA_TAG, data);
    }

    public static ResponseResult ok() {
        return new ResponseResult(ResponseCodeConst.SUCCESS);
    }

    public static ResponseResult ok(Object data) {
        return new ResponseResult(ResponseCodeConst.SUCCESS, data);
    }

    public static ResponseResult ok(Object data, String msg) {
        return new ResponseResult(ResponseCodeConst.SUCCESS, msg, data);
    }

    public static ResponseResult wrap(ResponseCodeConst codeConst) {
        return new ResponseResult(codeConst);
    }

    public static ResponseResult wrap(ResponseCodeConst codeConst, Object data) {
        return new ResponseResult(codeConst, data);
    }

    public static ResponseResult wrap(ResponseCodeConst codeConst, String msg) {
        return new ResponseResult(codeConst, msg, null);
    }



}
