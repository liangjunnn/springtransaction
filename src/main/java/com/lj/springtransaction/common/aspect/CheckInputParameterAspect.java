package com.lj.springtransaction.common.aspect;

import com.alibaba.fastjson.JSONObject;
import com.lj.springtransaction.common.enums.ErrorMsgEnum;
import com.lj.springtransaction.common.exception.BizException;
import com.lj.springtransaction.common.utils.DFAUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @Description: DFA算法敏感词拦截切面
 * @ClassName: CheckInputParameterAspect
 * @Author: liang_jun
 * @Date: 2020/11/2 10:44
 */
@Aspect
@Component
@Slf4j
public class CheckInputParameterAspect {

    private DFAUtil dfaUtil;

    public CheckInputParameterAspect(DFAUtil dfaUtil) {
        this.dfaUtil = dfaUtil;
    }

    @Pointcut("execution(public * com.lj.springtransaction.controller..*.*(..))")
    public void params() {
    }

    /**
     * 前置通知
     *
     * @param
     * @throws Throwable
     */
    @Before("params()")
    public Object before(JoinPoint point) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        Object[] args = point.getArgs();

        for (Object o : args) {
            if (o instanceof HttpServletRequest || o instanceof HttpServletResponse) {
                continue;
            }
            String s = JSONObject.toJSONString(o);
            Set<String> sensitiveWordByDFAMap = dfaUtil.getSensitiveWordByDFAMap(StringUtils.join(s, ","), 1);
            log.info("敏感词有" + sensitiveWordByDFAMap.size() + "个");
            if (sensitiveWordByDFAMap.size() >= 1) {
                //自定义的异常
                throw new BizException(ErrorMsgEnum.CONTENT_SENSITIVE_CODE);
            }
            log.info("当前调用接口-[" + request.getRequestURL() + "]");
        }
        return args;
    }

}