package com.lj.springtransaction.common;

/**
 * @version : V1.0
 * @ClassName: Result
 * @Description:
 * @author:
 * @date: 2020/3/22 15:30
 */
public class Result<T> {

    /**
     * 处理是否成功
     **/
    private boolean success = true;
    /**
     * 接口错误码
     **/
    private String errorCode;
    /**
     * 接口错误信息
     **/
    private String errorMsg;
    /**
     * 业务数据
     **/
    private T data;

    public Result(String errorCode, String errorMsg) {
        super();
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.success = false;
    }

    public Result() {
        super();
    }

    public Result(T data) {
        super();
        this.success = true;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
