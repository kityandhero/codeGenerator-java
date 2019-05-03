// package com.lzt.operate.codetools.common;
//
// import org.apache.ibatis.session.SqlSessionFactory;
// import org.mybatis.spring.SqlSessionFactoryBean;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.sqlite.SQLiteDataSource;
//
// import javax.sql.DataSource;
//
// /**
//  * @author lzt
//  */
// @Configuration
// public class MyBatisConfig {
//
//     private DataSourceProperties dataSourceProperties;
//
//     @Autowired
//     public MyBatisConfig(DataSourceProperties dataSourceProperties) {
//         this.dataSourceProperties = dataSourceProperties;
//     }
//
//     @Bean(name = "dataSource")
//     public DataSource dataSource() {
//         SQLiteDataSource dataSource = new SQLiteDataSource();
//         dataSource.setUrl(this.dataSourceProperties.getUrl());
//         return dataSource;
//     }
//
//     public SqlSessionFactory sqlSessionFactory() throws Exception {
//         SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//         sqlSessionFactoryBean.setDataSource(this.dataSource());
//         return sqlSessionFactoryBean.getObject();
//     }
// }
