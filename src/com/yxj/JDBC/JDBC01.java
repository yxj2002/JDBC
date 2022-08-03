package com.yxj.JDBC;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


/**
 * @Author:zhili
 * @Time:2022/7/31 16:38
 * @describe:第一个JDBC程序，完成简单的操作
 */


public class JDBC01 {
    public static void main(String[] args) throws SQLException {
        //1.注册驱动
        Driver driver = new Driver();
        //2.得到连接
        //(1)jdbc:mysql://  规定好的协议，不要改动
        //(2)localhost:主机，可以是IP地址
        //(3)3306表示mysql监听的端口
        //(4)yxj_de03表示连接到那个数据库
        String url="jdbc:mysql://localhost:3306/yxj_de03";
        //将用户名和密码放入一个properties
        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","70118084");
        //
        Connection connect = driver.connect(url, properties);
        //3.执行sql语句
        String sql="insert into dog values (2,'lisa')";
        //statement用于执行sql静态语句并返回其生成的结果的对象
        Statement statement = connect.createStatement();
        int i = statement.executeUpdate(sql);//如果是dml语句返回的就是影响行数
        System.out.println(i>0?"成功":"失败");
        //关闭资源
        statement.close();
        connect.close();
    }
}
