/*
 * @author yangjiewei
 * @date 2020/11/14 16:04
 */
package com.yjiewei.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;


/**
 * 使用纯注解的方式来实现事务
 */
@Configuration
@ComponentScan("com.yjiewei")
@EnableTransactionManagement
public class SpringConfig {

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource datasource){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(datasource);
        return transactionManager;
    }

    @Bean
    public ComboPooledDataSource dataSource() throws Exception {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        comboPooledDataSource.setJdbcUrl("jdbc:mysql:///ssm?serverTimezone=GMT");
        comboPooledDataSource.setUser("root");
        comboPooledDataSource.setPassword("145792");
        return comboPooledDataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    @Bean
    public MapperScannerConfigurer mapperScanner(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.yjiewei.dao");
        return mapperScannerConfigurer;
    }
}
