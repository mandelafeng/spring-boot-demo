package com.cfhui.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = 6046499770215249469L;

    /**
     * 默认成功码
     */
    public static final int DEFAULT_SUCCESS_CODE = 200;

    /**
     * 默认错误码
     */
    public static final int DEFAULT_ERROR_CODE = -1;

    /**
     * 默认成功信息
     */
    public static final String DEFAULT_SUCCESS_MESSAGE = "操作成功";


    /**
     * 状态
     */
    private boolean success;

    /**
     * 错误码
     */
    private int code;

    /**
     * 消息
     */
    private String message;

    /**
     * 错误消息
     */
    private String error;

    /**
     * 数据
     */
    private T data;

    private ResponseResult(boolean success, int code, String message, String error, T object) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.error = error;
        this.data = object;
    }

    public static <T> ResponseResult<T> ok() {
        return ResponseResult.<T>Builder()
                .status(true)
                .code(DEFAULT_SUCCESS_CODE)
                .message(DEFAULT_SUCCESS_MESSAGE)
                .data(null)
                .error(null)
                .build();
    }

    public static <T> ResponseResult<T> ok(MessageEnum messageEnum) {
        return ResponseResult.<T>Builder()
                .status(true)
                .code(messageEnum.getCode())
                .message(messageEnum.getMessage())
                .data(null)
                .error(null)
                .build();
    }

    public static <T> ResponseResult<T> ok(T data) {
        return ResponseResult.<T>Builder()
                .status(true)
                .code(DEFAULT_SUCCESS_CODE)
                .message(DEFAULT_SUCCESS_MESSAGE)
                .data(data)
                .error(null)
                .build();
    }

    public static <T> ResponseResult<T> ok(String message) {
        return ResponseResult.<T>Builder()
                .status(true)
                .code(DEFAULT_SUCCESS_CODE)
                .message(message)
                .data(null)
                .error(null)
                .build();
    }

    public static <T> ResponseResult<T> ok(int code, String message) {
        return ResponseResult.<T>Builder()
                .status(true)
                .code(code)
                .message(message)
                .data(null)
                .error(null)
                .build();
    }

    public static <T> ResponseResult<T> ok(int code, String message, T data) {
        return ResponseResult.<T>Builder()
                .status(true)
                .code(code)
                .message(message)
                .data(data)
                .error(null)
                .build();
    }

    public static <T> ResponseResult<T> error(String error) {
        return ResponseResult.<T>Builder()
                .status(false)
                .code(DEFAULT_ERROR_CODE)
                .error(error)
                .data(null)
                .build();
    }

    public static <T> ResponseResult<T> error(MessageEnum messageEnum) {
        return ResponseResult.<T>Builder()
                .status(false)
                .code(messageEnum.getCode())
                .error(messageEnum.getMessage())
                .data(null)
                .build();
    }

    public static <T> ResponseResult<T> error(int code, String error) {
        return ResponseResult.<T>Builder()
                .status(false)
                .code(code)
                .data(null)
                .error(error)
                .build();
    }

    ResponseResult(Builder<T> builder) {
        this.success = builder.success;
        this.code = builder.code;
        this.message = builder.message;
        this.error = builder.error;
        this.data = builder.data;
    }

    public static <T> Builder<T> Builder() {
        return new Builder<>();
    }

    public static final class Builder<T> {

        private boolean success;
        private int code;
        private String message;
        private String error;
        private T data;

        Builder() {}

        Builder(ResponseResult<T> responseResult) {
            this.success = responseResult.success;
            this.code = responseResult.code;
            this.message = responseResult.message;
            this.error = responseResult.error;
            this.data = responseResult.data;
        }

        public Builder<T> status(boolean status) {
            this.success = status;
            return this;
        }

        public Builder<T> code(int code) {
            this.code = code;
            return this;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> error(String error) {
            this.error = error;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public ResponseResult<T> build() {
            return new ResponseResult<T>(this);
        }
    }

}
