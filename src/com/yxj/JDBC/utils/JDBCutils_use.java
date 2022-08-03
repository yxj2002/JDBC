package com.yxj.JDBC.utils;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author:zhili
 * @Time:2022/8/1 16:55
 * @describe:测试JDBCutils的使用
 */
public class JDBCutils_use {

    @Test
    public void text_JDBCutils() throws SQLException {
        Connection connection = JDBCutils.getConnection();
        String sql="insert into admire values (null,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,"yxj");
        preparedStatement.setString(2,"sadasd");
        int i = preparedStatement.executeUpdate();
        System.out.println(i>0?"成功！":"失败！");
        JDBCutils.close(connection,preparedStatement,null);
    }
}
