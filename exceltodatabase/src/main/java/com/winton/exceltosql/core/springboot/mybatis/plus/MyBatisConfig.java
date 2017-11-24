package com.winton.exceltosql.core.springboot.mybatis.plus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.winton.exceltosql.model.mapper")
public class MyBatisConfig {

}