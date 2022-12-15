package com.cfhui.exception;

import com.cfhui.model.entity.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.stream.Collectors;

/**
 * @author cfhui
 * @version V1
 * @description 全局异常处理
 * @date 2022/12/13 14:24
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandel {

    @ExceptionHandler(value = {BindException.class, ValidationException.class, MethodArgumentNotValidException.class})
    public ResponseBean handleValidHandel(Exception e) {
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            return ResponseBean.error(HttpStatus.BAD_REQUEST.value(),
                ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining("; ")),
                null
            );
        } else if (e instanceof ConstraintViolationException) {
            ConstraintViolationException ex = (ConstraintViolationException) e;
            return ResponseBean.error(HttpStatus.BAD_REQUEST.value(),
                ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("; ")),
                null
            );
        } else if (e instanceof BindException) {
            BindException ex = (BindException) e;
            return ResponseBean.error(HttpStatus.BAD_REQUEST.value(),
                ex.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining("; ")),
                null
            );
        }
        // TODO: 2022/12/13 hcf 这块处理不对
        return ResponseBean.success();
    }

}
