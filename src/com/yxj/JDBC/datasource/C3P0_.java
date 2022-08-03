package com.yxj.JDBC.datasource;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.jupiter.api.Test;

import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author:zhili
 * @Time:2022/8/2 0:58
 * @describe:演示C3P0的使用
 */
public class C3P0_ {

    @Test
    public void testC3P0_01() throws IOException, PropertyVetoException, SQLException {
        //创建一个数据源对象
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        //通过配置文件mysql.properties获取相关连接的对象
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.Properties"));
        String user=properties.getProperty("user");
        String password=properties.getProperty("password");
        String url=properties.getProperty("url");
        String driver=properties.getProperty("driver");

        //给数据源 comboPooledDataSource设置相关参数
        comboPooledDataSource.setDriverClass(driver);
        comboPooledDataSource.setJdbcUrl(url);
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(password);

        //设置初始化连接数
        comboPooledDataSource.setInitialPoolSize(10);
        //设置最大连接数
        comboPooledDataSource.setMaxPoolSize(50);
        long l = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            Connection connection = comboPooledDataSource.getConnection();
            //System.out.println("连接成功！");
            connection.close();
        }
        long r = System.currentTimeMillis();
        System.out.println("耗时："+(r-l));
    }


    //第二张方法：使用配置文件模板
    @Test
    public void testC3P0_02() throws SQLException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        long l = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            Connection connection = comboPooledDataSource.getConnection();
            //System.out.println("连接成功！");
            connection.close();
        }
        long r = System.currentTimeMillis();
        System.out.println("耗时"+(r-l));

    }


}
