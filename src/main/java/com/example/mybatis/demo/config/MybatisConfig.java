package com.example.mybatis.demo.config;

import com.example.mybatis.demo.extend.CustomSqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisConfig {

    @Bean
    public CustomSqlInjector customSqlInjector() {
        return new CustomSqlInjector();
    }
}
