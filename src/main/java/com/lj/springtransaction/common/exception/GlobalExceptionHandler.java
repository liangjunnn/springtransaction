package com.lj.springtransaction.common.exception;


import com.lj.springtransaction.common.Result;
import com.lj.springtransaction.common.enums.ErrorMsgEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.sql.SQLException;


/**
 * @Description: 全局异常
 * @ClassName: GlobalExceptionHandler
 * @Author: liang_jun
 * @Date: 2020/7/23 10:42
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({UserException.class, CustomException.class})
    Result<String> handleException(CustomException e) {
        log.error(String.valueOf(e), e);
        return new Result<String>(e.getExceptionCode().getCode(), e.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BindException.class)
    Result<String> handleException(BindException e) {
        log.error(String.valueOf(e), e);
        return new Result<String>(ErrorMsgEnum.PARAMETER_EXCEPTION.getCode(), e.getFieldError().getDefaultMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    Result<String> handleException(HttpRequestMethodNotSupportedException e) {
        log.error(String.valueOf(e), e);
        return new Result<String>(String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()), HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({BadSqlGrammarException.class, DataIntegrityViolationException.class})
    Result<String> handleException(RuntimeException e) {
        log.error(String.valueOf(ErrorMsgEnum.EER_DB), e);
        return new Result<String>(ErrorMsgEnum.EER_DB.getCode(), String.format(ErrorMsgEnum.EER_DB.getMsg(), e.getCause().getMessage()));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({SQLException.class})
    Result<String> handleException(SQLException e) {
        log.error(String.valueOf(ErrorMsgEnum.EER_DB), e);
        return new Result<String>(ErrorMsgEnum.EER_DB.getCode(), String.format(ErrorMsgEnum.EER_DB.getMsg(), e.getCause().getMessage()));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({Exception.class})
    Result<String> handleException(Exception e) {
        Result<String> result = new Result<String>();

        if (e instanceof NullPointerException) {
            log.error(String.valueOf(ErrorMsgEnum.ERR_1005), e);
            result = new Result<String>(ErrorMsgEnum.ERR_1005.getCode(), ErrorMsgEnum.ERR_1005.getMsg());
        } else if (e instanceof ArithmeticException) {
            log.error(String.valueOf(ErrorMsgEnum.ERR_1004), e);
            result = new Result<String>(ErrorMsgEnum.ERR_1004.getCode(), ErrorMsgEnum.ERR_1004.getMsg());
        } else if (e instanceof ClassCastException) {
            log.error(String.valueOf(ErrorMsgEnum.ERR_1002), e);
            result = new Result<String>(ErrorMsgEnum.ERR_1002.getCode(), ErrorMsgEnum.ERR_1002.getMsg());
        } else if (e instanceof ArrayIndexOutOfBoundsException) {
            log.error(String.valueOf(ErrorMsgEnum.ERR_1003), e);
            result = new Result<String>(ErrorMsgEnum.ERR_1003.getCode(), ErrorMsgEnum.ERR_1003.getMsg());
        } else if (e instanceof NumberFormatException) {
            log.error(String.valueOf(ErrorMsgEnum.ERR_1001), e);
            result = new Result<String>(ErrorMsgEnum.ERR_1001.getCode(), ErrorMsgEnum.ERR_1001.getMsg());
        } else if (e instanceof NegativeArraySizeException) {
            log.error(String.valueOf(ErrorMsgEnum.ERR_1007), e);
            result = new Result<String>(ErrorMsgEnum.ERR_1007.getCode(), ErrorMsgEnum.ERR_1007.getMsg());
        } else if (e instanceof IOException) {
            log.error(String.valueOf(ErrorMsgEnum.ERR_1008), e);
            result = new Result<String>(ErrorMsgEnum.ERR_1008.getCode(), ErrorMsgEnum.ERR_1008.getMsg());
        } else if (e instanceof ArrayStoreException) {
            log.error(String.valueOf(ErrorMsgEnum.ERR_1009), e);
            result = new Result<String>(ErrorMsgEnum.ERR_1009.getCode(), ErrorMsgEnum.ERR_1009.getMsg());
        } else if (e instanceof IllegalArgumentException) {
            log.error(String.valueOf(ErrorMsgEnum.ERR_ILLEGAL_ARGUMENT), e);
            result = new Result<String>(ErrorMsgEnum.ERR_ILLEGAL_ARGUMENT.getCode(), ErrorMsgEnum.ERR_ILLEGAL_ARGUMENT.getMsg());
        } else if (e instanceof HttpMessageNotReadableException) {
            log.error(String.valueOf(ErrorMsgEnum.ERR_ILLEGAL_ARGUMENT), e);
            result = new Result<String>(ErrorMsgEnum.ERR_ILLEGAL_ARGUMENT.getCode(), ErrorMsgEnum.ERR_ILLEGAL_ARGUMENT.getMsg());
        } else {
            log.error(String.valueOf(ErrorMsgEnum.ERR_1000), e);
            result = new Result<String>(ErrorMsgEnum.ERR_1000.getCode(), ErrorMsgEnum.ERR_1000.getMsg());
        }
        result.setSuccess(false);
        return result;
    }


}
