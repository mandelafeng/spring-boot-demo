package com.cfhui.exception;


import com.cfhui.dto.MessageEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseErrorException extends RuntimeException {

    private static final long serialVersionUID = 6386720492655133851L;
    private int code;
    private String error;

    public BaseErrorException(MessageEnum messageEnum) {
        this.code = messageEnum.getCode();
        this.error = messageEnum.getMessage();
    }
}
