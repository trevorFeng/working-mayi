package com.mayi.transfer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource.druid.crediting")
public class CreditingDataSourceProperties {
    private String url;

    private String username;

    private String password;

    private String driverClassName;

    private String type;

    private Integer initialSize;

    private Integer minIdle;

    private Integer maxActive;

    private Integer maxWait;

    private Integer timeBetweenEvictionRunsMillis;

    private Integer minEvictableIdleTimeMillis;

    private String validationQuery;

    private Boolean testWhileIdle;

    private String testOnBorrow;

    private String testOnReturn;

    private String poolPreparedStatements;

    private String filters;

    private String connectionProperties;

    private String initConnectionSqls;
}
