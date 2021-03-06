package com.mayi.transfer.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.mayi.transfer.constants.DBConstants;
import com.mysql.cj.jdbc.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = DBConstants.CREDITING_MAPPER, sqlSessionFactoryRef = DBConstants.CREDITING_SQL_SESSION_FACTORY)
public class CreditingDataSourceConfiguration {

    @Resource
    private CreditingDataSourceProperties creditingDataSourceProperties;

    @Bean(DBConstants.CREDITING_DATA_SOURCE)
    public DataSource secondDataSource() {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(creditingDataSourceProperties.getUrl());
        mysqlXaDataSource.setPassword(creditingDataSourceProperties.getPassword());
        mysqlXaDataSource.setUser(creditingDataSourceProperties.getUsername());

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName(DBConstants.CREDITING_DATA_SOURCE);
        xaDataSource.setPoolSize(creditingDataSourceProperties.getInitialSize());
        xaDataSource.setMinPoolSize(creditingDataSourceProperties.getMinIdle());
        xaDataSource.setMaxPoolSize(creditingDataSourceProperties.getMaxActive());
        xaDataSource.setMaxIdleTime(creditingDataSourceProperties.getMinIdle());
        xaDataSource.setMaxLifetime(creditingDataSourceProperties.getMinEvictableIdleTimeMillis());
        xaDataSource.setConcurrentConnectionValidation(creditingDataSourceProperties.getTestWhileIdle());
        xaDataSource.setTestQuery(creditingDataSourceProperties.getValidationQuery());

        return xaDataSource;
    }

    @Bean(DBConstants.CREDITING_SQL_SESSION_FACTORY)
    public SqlSessionFactory secondSqlSessionFactory(@Qualifier(DBConstants.CREDITING_DATA_SOURCE) DataSource secondDataSource)
            throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(secondDataSource);
        bean.setTypeAliasesPackage(DBConstants.CREDITING_MAPPER);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(DBConstants.CREDITING_MAPPER_XML));

        return bean.getObject();
    }
}
