package com.cssl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan(basePackages = "com.cssl.dao")
@ServletComponentScan("com.cssl.util")
@EnableTransactionManagement
public class Maven_App {
    public static void main(String[] args){
        SpringApplication.run(Maven_App.class, args);
    }

}
