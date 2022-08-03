package com.yxj.JDBC.datasource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

/**
 * @Author:zhili
 * @Time:2022/8/2 14:50
 * @describe:测试德鲁伊工具类
 */
public class Druid_ {

    @Test
    public void testDruid() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\Druid.properties"));
        //创建一个指定参数的数据库连接池
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        long l = System.currentTimeMillis();
        for (int i = 0; i < 5000000; i++) {
            Connection connection = dataSource.getConnection();
            //System.out.println("连接成功！");
            connection.close();
        }
        long r = System.currentTimeMillis();
        System.out.println("耗时"+(r-l));
        //耗时997ms(500 0000次连接)
    }
}
