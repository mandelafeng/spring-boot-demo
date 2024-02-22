package com.cfhui.exception;


import com.cfhui.dto.MessageEnum;
import lombok.Data;

@Data
public class BusinessException extends BaseErrorException {

    private static final long serialVersionUID = 2369773524406947262L;

    public BusinessException(MessageEnum messageEnum) {
        super(messageEnum);
    }

    public BusinessException(String error) {
        super.setCode(-1);
        super.setError(error);
    }
}
