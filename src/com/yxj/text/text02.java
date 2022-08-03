package com.yxj.text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * @Author:zhili
 * @Time:2022/8/1 14:56
 * @describe:
 */
public class text02 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Scanner scanner = new Scanner(System.in);
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.Properties"));
        String user=properties.getProperty("user");
        String password=properties.getProperty("password");
        String url=properties.getProperty("url");
        String driver=properties.getProperty("driver");
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        //添加数据
        String sql01="insert into admire values(null,?,?)";
        //修改数据
        String sql02="update admire set name='king' where name ='tom'";
        //删除数据
        String sql03="delete form admire where name='tom'";
        //查询记录
        String sql04="select * from admire";
        PreparedStatement preparedStatement = connection.prepareStatement(sql04);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            int id=resultSet.getInt("id");
            String name=resultSet.getString("name");
            String content=resultSet.getString("content");
            System.out.println(id+"\t"+name+"\t"+content);
        }
        connection.close();
        preparedStatement.close();
        resultSet.close();
    }

}
