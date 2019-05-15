package com.tensquare.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

@SpringBootApplication //注解
public class BaseApplication {
    /**
     * 入口文件
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }

    /**
     * 构造id生成器
     * @return
     */
    @Bean //让返回值在容器中
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
