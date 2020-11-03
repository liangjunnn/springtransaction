package com.lj.springtransaction.common.enums;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public enum ErrorMsgEnum {
    /**
     * 接口异常导致服务不可用
     */
    SERVICE_UNAVAILABILITY("100000", "服务不可用"),

    /**
     * 参数异常
     */
    PARAMETER_EXCEPTION("100001", "参数异常"),


    /**
     * 数据库操作异常
     */
    EER_DB("100002", "数据库操作异常, %s"),

    /**
     * 方法参数错误异常
     */
    ERR_ILLEGAL_ARGUMENT("100003", "方法参数错误异常"),

    /**
     * 用户不存在
     */
    USER_INEXISTENCE_ERROR("100004", "用户不存在"),

    /**
     * 您输入的内容有敏感词
     */
    CONTENT_SENSITIVE_CODE("100005", "您输入的内容有敏感词"),

    /**
     * 未知异常
     */
    ERR_1000("1000001", "服务异常"),

    /**
     * 字符串转换为数字异常
     */
    ERR_1001("1000002", "字符串转换为数字异常"),

    /**
     * 类型强制转换异常
     */
    ERR_1002("1000003", "类型强制转换异常"),

    /**
     * 数组下标越界异常
     */
    ERR_1003("1000004", "数组下标越界异常"),

    /**
     * 算数计算异常
     */
    ERR_1004("1000005", "算数计算异常"),

    /**
     * 空指针异常
     */
    ERR_1005("1000006", "空指针异常"),

    /**
     * 数组负下标异常
     */
    ERR_1007("1000007", "数组负下标异常"),

    /**
     * IO流异常
     */
    ERR_1008("1000008", "IO流异常"),

    /**
     * 数组存储异常
     */
    ERR_1009("1000009", "数组存储异常");
    /**
     * 错误编码为key的所有错误枚举对象的map集合
     */
    private static Map<String, String> enums = new HashMap<>(ErrorMsgEnum.values().length);

    static {
        for (ErrorMsgEnum e : ErrorMsgEnum.values()) {
            enums.put(e.getCode(), e.getMsg());
        }
    }

    /**
     * 通过错误编码获取错误消息内容
     *
     * @param code 错误编码
     * @return 错误消息内容
     */
    public static String getMsgByCode(String code) {
        return enums.get(code);
    }

    /**
     * 通过错误码枚举，返回一个格式化后的错误信息
     *
     * @param constants 错误码枚举
     * @param args      错误信息格式中需要的参数
     * @return 格式化后的错误信息
     */
    public static String getMsg(ErrorMsgEnum constants, Object... args) {
        return MessageFormat.format(constants.getMsg(), args);
    }

    /**
     * 错误编码
     */
    private String code;
    /**
     * 错误消息提示
     */
    private String msg;

    ErrorMsgEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
