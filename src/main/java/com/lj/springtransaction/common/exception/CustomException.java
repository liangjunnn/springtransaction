package com.lj.springtransaction.common.exception;


import com.lj.springtransaction.common.enums.ErrorMsgEnum;

/**
 * @Description: 自定义异常
 * @ClassName: CustomException
 * @Author: liang_jun
 * @Date: 2020/7/23 10:35
 */
public class CustomException extends RuntimeException {

    private ErrorMsgEnum code;

    public CustomException(ErrorMsgEnum code) {
        super(code.getMsg());
        this.code = code;
    }

    public CustomException(ErrorMsgEnum code, Object... args) {
        super(code.getMsg(code, args));
        this.code = code;
    }

    public CustomException(ErrorMsgEnum code, Throwable t) {
        super(code.getMsg(), t);
        this.code = code;
    }

    public ErrorMsgEnum getExceptionCode() {
        return code;
    }

    @Override
    public String toString() {
        if (null != code) {
            String format = String.format("%s : %s", code.getCode(), super.getMessage());
            return format;
        }
        return "";
    }

}
