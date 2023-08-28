package com.cfhui.encrypt.sm.sm4;

public enum BaseSmEnum {

    /**
     *
     */
    ENCODING("UTF-8"),
    ALGORITHM_NAME("SM4"),
    ALGORITHM_NAME_CBC_PADDING("SM4/CBC/PKCS7Padding"),
    ALGORITHM_NAME_ECB_PADDING("SM4/ECB/PKCS5Padding"),

    ;


    private String msg;

    BaseSmEnum(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "BaseEnum{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
