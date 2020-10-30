package com.lj.springtransaction;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lj.springtransaction.mapper")
public class SpringtransactionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringtransactionApplication.class, args);
    }

}
