package com.lj.springtransaction.common.exception;


import com.lj.springtransaction.common.enums.ErrorMsgEnum;

/**
 * @Description: 用户异常
 * @ClassName: UserException
 * @Author: liang_jun
 * @Date: 2020/8/28 11:13
 */
public class UserException extends CustomException {
    public UserException(ErrorMsgEnum code) {
        super(code);
    }

    public UserException(ErrorMsgEnum code, Object... args) {
        super(code, args);
    }

    public UserException(ErrorMsgEnum code, Throwable t) {
        super(code, t);
    }

}
