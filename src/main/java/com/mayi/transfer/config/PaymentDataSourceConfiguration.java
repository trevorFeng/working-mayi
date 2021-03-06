package com.mayi.transfer.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.mayi.transfer.constants.DBConstants;
import com.mysql.cj.jdbc.MysqlXADataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;


@Configuration
@MapperScan(basePackages = DBConstants.PAYMENT_MAPPER, sqlSessionFactoryRef = DBConstants.PAYMENT_SQL_SESSION_FACTORY)
@Slf4j
public class PaymentDataSourceConfiguration {

    @Resource
    private PaymentDataSourceProperties paymentDataSourceProperties;


    @Primary
    @Bean(DBConstants.PAYMENT_DATA_SOURCE)
    public DataSource PAYMENTDataSource() {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(paymentDataSourceProperties.getUrl());
        mysqlXaDataSource.setPassword(paymentDataSourceProperties.getPassword());
        mysqlXaDataSource.setUser(paymentDataSourceProperties.getUsername());

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName(DBConstants.PAYMENT_DATA_SOURCE);
        xaDataSource.setPoolSize(paymentDataSourceProperties.getInitialSize());
        xaDataSource.setMinPoolSize(paymentDataSourceProperties.getMinIdle());
        xaDataSource.setMaxPoolSize(paymentDataSourceProperties.getMaxActive());
        xaDataSource.setMaxIdleTime(paymentDataSourceProperties.getMinIdle());
        xaDataSource.setMaxLifetime(paymentDataSourceProperties.getMinEvictableIdleTimeMillis());
        xaDataSource.setConcurrentConnectionValidation(paymentDataSourceProperties.getTestWhileIdle());
        xaDataSource.setTestQuery(paymentDataSourceProperties.getValidationQuery());

        return xaDataSource;
    }

    @Primary
    @Bean(DBConstants.PAYMENT_SQL_SESSION_FACTORY)
    public SqlSessionFactory PAYMENTSqlSessionFactory(@Qualifier(DBConstants.PAYMENT_DATA_SOURCE) DataSource PAYMENTDataSource)
            throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(PAYMENTDataSource);
        bean.setTypeAliasesPackage(DBConstants.PAYMENT_MAPPER);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(DBConstants.PAYMENT_MAPPER_XML));
        return bean.getObject();
    }
}
