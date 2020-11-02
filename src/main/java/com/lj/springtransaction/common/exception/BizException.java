package com.lj.springtransaction.common.exception;

import com.lj.springtransaction.common.enums.ErrorMsgEnum;

/**
 * @Description:
 * @ClassName: BizException
 * @Author: liang_jun
 * @Date: 2020/11/2 10:50
 */
public class BizException extends CustomException {
    public BizException(ErrorMsgEnum code) {
        super(code);
    }

    public BizException(ErrorMsgEnum code, Object... args) {
        super(code, args);
    }

    public BizException(ErrorMsgEnum code, Throwable t) {
        super(code, t);
    }
}
