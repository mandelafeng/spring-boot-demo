package com.cfhui.model;

import lombok.Data;
import org.apache.http.HttpStatus;

@Data
public class ResponseBean {
    /**
     * 响应状态码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 返回数据
     */
    private Object data;

    public static ResponseBean success() {
        return new ResponseBean(HttpStatus.SC_OK, "");
    }
    public static ResponseBean success(String message) {
        return new ResponseBean(HttpStatus.SC_OK, message);
    }

    public static ResponseBean success(String message, Object data) {
        return new ResponseBean(HttpStatus.SC_INTERNAL_SERVER_ERROR, message, data);
    }

    public static ResponseBean error(String message) {
        return new ResponseBean(HttpStatus.SC_INTERNAL_SERVER_ERROR, message);
    }

    public static ResponseBean error(String message, Object data) {
        return new ResponseBean(500, message, data);
    }

    //私有的构造器 只能通过本类的方法创建对象
    private ResponseBean(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private ResponseBean(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
