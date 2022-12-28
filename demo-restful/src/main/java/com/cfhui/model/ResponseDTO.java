package com.cfhui.model;


import com.cfhui.constant.ResponseCodeConst;
import lombok.Data;

/**
 * @description 通用响应返回
 * @author cfhui
 * @version V1
 * @date 2022/12/28 14:20
 */
@Data
public class ResponseDTO<T> {

    protected Integer code;

    protected String msg;

    protected Boolean success;

    protected T data;

    public ResponseDTO(ResponseCodeConst responseCodeConst, String msg) {
        this.code = responseCodeConst.getCode();
        this.msg = msg;
        this.success = responseCodeConst.isSuccess();
    }

    public ResponseDTO(ResponseCodeConst responseCodeConst, T data) {
        super();
        this.code = responseCodeConst.getCode();
        this.msg = responseCodeConst.getMsg();
        this.data = data;
        this.success = responseCodeConst.isSuccess();
    }

    public ResponseDTO(ResponseCodeConst responseCodeConst, T data, String msg) {
        super();
        this.code = responseCodeConst.getCode();
        this.msg = msg;
        this.data = data;
        this.success = responseCodeConst.isSuccess();
    }

    private ResponseDTO(ResponseCodeConst responseCodeConst) {
        this.code = responseCodeConst.getCode();
        this.msg = responseCodeConst.getMsg();
        this.success = responseCodeConst.isSuccess();
    }

    public ResponseDTO(ResponseDTO responseDTO) {
        this.code = responseDTO.getCode();
        this.msg = responseDTO.getMsg();
        this.success = responseDTO.isSuccess();
    }

    public static <T> ResponseDTO<T> success() {
        return new ResponseDTO<T>(ResponseCodeConst.SUCCESS);
    }

    public static <T> ResponseDTO<T> success(T data, String msg) {
        return new ResponseDTO<T>(ResponseCodeConst.SUCCESS, data, msg);
    }
    public static <T> ResponseDTO<T> success(T data) {
        return new ResponseDTO<T>(ResponseCodeConst.SUCCESS, data);
    }

    public static <T> ResponseDTO<T> success(String msg) {
        return new ResponseDTO<T>(ResponseCodeConst.SUCCESS, msg);
    }
    public static <T> ResponseDTO<T> wrap(ResponseCodeConst codeConst) {
        return new ResponseDTO<>(codeConst);
    }

    public static <T> ResponseDTO<T> wrap(ResponseCodeConst codeConst, T t) {
        return new ResponseDTO<T>(codeConst, t);
    }

    public static <T> ResponseDTO<T> wrap(ResponseCodeConst codeConst, String msg) {
        return new ResponseDTO<T>(codeConst, msg);
    }

    public static <T> ResponseDTO<T> wrapMsg(ResponseCodeConst codeConst, String msg) {
        return new ResponseDTO<T>(codeConst, msg);
    }

    public boolean isSuccess() {
        return success;
    }
}
