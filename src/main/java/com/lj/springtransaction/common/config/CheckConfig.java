package com.lj.springtransaction.common.config;

import com.lj.springtransaction.common.utils.DFAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: DFA算法实现敏感词过滤
 * @ClassName: CheckConfig
 * @Author: liang_jun
 * @Date: 2020/11/2 10:39
 */
@Configuration
public class CheckConfig {
    private DFAUtil dfaUtil;

    @Autowired
    public CheckConfig(DFAUtil dfaUtil) {
        this.dfaUtil = dfaUtil;
    }

    @Bean
    public void init() {
        Set<String> set = new HashSet<>();
        set.add("鸡");
        set.add("鸭");
        set.add("毛泽东");
        set.add("台独");
        set.add("微信");
        //将集合放到算法里，这里可以优化，写词库文件等等，
        dfaUtil.createDFAHashMap(set);
    }

}
