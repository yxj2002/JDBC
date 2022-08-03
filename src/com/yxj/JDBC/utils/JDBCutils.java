package com.yxj.JDBC.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @Author:zhili
 * @Time:2022/8/1 16:29
 * @describe:这是一个工具类，完成mysql的连接和关闭
 */
public class JDBCutils {
    private static String user;
    private static String password;
    private static String url;
    private static String driver;

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src\\mysql.Properties"));
            user=properties.getProperty("user");
            password=properties.getProperty("password");
            url=properties.getProperty("url");
            driver=properties.getProperty("driver");
        } catch (IOException e) {
            //在实际开发中可以将编译异常转成运行一次，这是编译者可以现在捕获该异常，也可以默认处理
            throw new RuntimeException(e);
        }
    }

    //连接数据库
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }

    //关闭相关资源
    public static void close(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        if (connection != null) {
            connection.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (resultSet != null) {
            resultSet.close();
        }
    }
}



