package com.lj.springtransaction.common.aspect;

import com.lj.springtransaction.common.annotation.MyTransaction;
import com.lj.springtransaction.common.utils.TransactionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 事务切面
 * @ClassName: TransactionAspect
 * @Author: liang_jun
 * @Date: 2020/10/29 16:23
 */
@Aspect
@Component
public class TransactionAspect {

    private TransactionUtils transactionUtils;

    @Autowired
    public TransactionAspect(TransactionUtils transactionUtils) {
        this.transactionUtils = transactionUtils;
    }

    /**
     * 切入点
     *
     * @return void
     * @author liang_jun
     * @date 2020/10/29 16:25
     */
    @Pointcut("execution(public * com.lj.springtransaction.service..*.*(..))")
    public void TransactionPointCut() {
    }

    //异常通知：给添加事务的方法回滚事务，当方法抛出异常时
    @AfterThrowing("TransactionPointCut()")
    public void rollbackTransaction(JoinPoint joinPoint) {
        //获取类注解
        MyTransaction annotation = joinPoint.getTarget().getClass().getAnnotation(MyTransaction.class);
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取方法注解
        MyTransaction annotation1 = signature.getMethod().getAnnotation(MyTransaction.class);
        if (null != annotation || null != annotation1) {
            transactionUtils.rollBack();
        }
    }

    /**
     * 环绕通知
     *
     * @author liang_jun
     * @date 2020/10/29 16:50
     */
    @Around("TransactionPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        TransactionStatus transactionStatus;
        //获取类注解
        MyTransaction annotation = joinPoint.getTarget().getClass().getAnnotation(MyTransaction.class);
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取方法注解
        MyTransaction annotation1 = signature.getMethod().getAnnotation(MyTransaction.class);
        if (null != annotation || null != annotation1) {
            transactionStatus = transactionUtils.begin();
        } else {
            return joinPoint.proceed();
        }
        Object proceed = joinPoint.proceed();
        transactionUtils.commit(transactionStatus);
        return proceed;
    }

    /**
     * 获取目标对象的方法
     *
     * @return java.lang.reflect.Method
     * @author liang_jun
     * @date 2020/10/29 16:49
     */
    private Method getInvokedMethod(Class targetCls, ProceedingJoinPoint pJoinPoint) {
        List<Class<? extends Object>> clazzList = new ArrayList<>();
        Object[] args = pJoinPoint.getArgs();
        for (Object arg : args) {
            clazzList.add(arg.getClass());
        }

        Class[] argsCls = (Class[]) clazzList.toArray(new Class[0]);

        String methodName = pJoinPoint.getSignature().getName();
        Method method = null;
        try {
            method = targetCls.getMethod(methodName, argsCls);
        } catch (NoSuchMethodException e) {
            //不做任何处理,这个切面只处理事务相关逻辑
            //其他任何异常不在这个切面的考虑范围
        }
        return method;
    }

}
