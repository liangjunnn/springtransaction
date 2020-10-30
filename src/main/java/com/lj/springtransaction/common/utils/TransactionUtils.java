package com.lj.springtransaction.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

/**
 * @Description: 事务
 * @ClassName: TransactionUtils
 * @Author: liang_jun
 * @Date: 2020/10/29 16:20
 */
@Component
@Scope("prototype")//定义为多例 每个事务都是新的实例 目的解决线程安全问题
public class TransactionUtils {
    // 全局接受事务状态
    private TransactionStatus transactionStatus;

    // 获取事务源
    private DataSourceTransactionManager dataSourceTransactionManager;

    @Autowired
    public TransactionUtils(DataSourceTransactionManager dataSourceTransactionManager) {
        this.dataSourceTransactionManager = dataSourceTransactionManager;
    }


    //开启事务
    public TransactionStatus begin() {
        transactionStatus = dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
        return transactionStatus;
    }

    //提交事务
    public void commit(TransactionStatus transaction) {
        dataSourceTransactionManager.commit(transaction);
    }

    //回滚事务
    public void rollBack() {
        dataSourceTransactionManager.rollback(transactionStatus);
    }

}
