package com.yxj.JDBC.resultset_;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @Author:zhili
 * @Time:2022/7/31 23:05
 * @describe:
 */
public class ResultSet_ {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.Properties"));
        String user=properties.getProperty("user");
        String password=properties.getProperty("password");
        String url=properties.getProperty("url");
        String driver=properties.getProperty("driver");
        Class.forName(driver);
        //得到连接
        Connection connection = DriverManager.getConnection(url, user, password);
        //得到statement
        Statement statement = connection.createStatement();
        String sql="select id,content from news";
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){//让光标向后移动，如果没有更多的行返回false
            int id = resultSet.getInt(1);
            String content = resultSet.getString(2);
            System.out.println(id+"\t"+content);
        }
        resultSet.close();
        connection.close();
        statement.close();
    }
}
