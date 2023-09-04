package com.ronnyz.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * The class description.
 *
 * @author Ronnyz
 * @since 2023/9/1
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.ronnyz.mappers")
public class DataSourceConfig {

}