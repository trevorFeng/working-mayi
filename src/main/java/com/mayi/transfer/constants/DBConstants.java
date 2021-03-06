package com.mayi.transfer.constants;

public class DBConstants {

    public static final String PAYMENT_DATA_SOURCE = "paymentDataSource";
    public static final String CREDITING_DATA_SOURCE = "creditingDataSource";

    public static final String PAYMENT_SQL_SESSION_FACTORY = "paymentSqlSessionFactory";
    public static final String CREDITING_SQL_SESSION_FACTORY = "creditingSqlSessionFactory";


    public static final String PAYMENT_MAPPER = "com.mayi.transfer.dao.payment.mapper";
    public static final String CREDITING_MAPPER = "com.mayi.transfer.dao.crediting.mapper";

    public static final String PAYMENT_MAPPER_XML = "classpath:payment-mapper/*.xml";
    public static final String CREDITING_MAPPER_XML = "classpath:crediting-mapper/*.xml";
}
