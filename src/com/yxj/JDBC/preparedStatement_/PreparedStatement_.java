package com.yxj.JDBC.preparedStatement_;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * @Author:zhili
 * @Time:2022/8/1 14:00
 * @describe:演示PreparedStatement的使用
 */
public class PreparedStatement_ {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入姓名：");
        String admire_name=scanner.nextLine();
        System.out.print("请输入类容：");
        String admire_content=scanner.nextLine();
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.Properties"));
        String user=properties.getProperty("user");
        String password=properties.getProperty("password");
        String url=properties.getProperty("url");
        String driver=properties.getProperty("driver");
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        String sql="select * from admire where name=? and content=?";
        //preparedStatement对象实现了PreparedStatement接口的实现类对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,admire_name);
        preparedStatement.setString(2,admire_content);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            System.out.println("登录成功！");
        }else{
            System.out.println("登录失败！");
        }
        connection.close();
        preparedStatement.close();
        resultSet.close();
    }
}
