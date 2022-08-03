package com.yxj.text;

import com.mysql.cj.jdbc.Driver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @Author:zhili
 * @Time:2022/7/31 21:51
 * @describe:
 */
public class text01 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.Properties"));
        String user=properties.getProperty("user");
        String password=properties.getProperty("password");
        String url=properties.getProperty("url");
        String driver=properties.getProperty("driver");
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        String sql01="create table news (id int,content varchar(32))";
        String sql02="insert into news values (1,'lisa'),(2,'tom'),(3,'kitty'),(4,'peter'),(5,'opera')";
        String sql03="update news set content='wawa' where id=1";
        String sql04="delete from news where id=3";
        Statement statement = connection.createStatement();
        int i = statement.executeUpdate(sql04);//如果是dml语句返回的就是影响行数
        System.out.println(i>0?"成功":"失败");
        statement.close();
        connection.close();

    }




}
