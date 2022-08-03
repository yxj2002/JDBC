package com.yxj.JDBC.transaction;

import com.yxj.JDBC.utils.JDBCutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author:zhili
 * @Time:2022/8/1 19:29
 * @describe:
 */
public class transaction_ {
    public static void main(String[] args) throws SQLException {
        Connection connection = JDBCutils.getConnection();
        PreparedStatement preparedStatement=null;
        PreparedStatement preparedStatement1=null;
        try {
            String sql01="update actor set money = money-100 where name='lisa'";
            String sql02="update actor set money = money+100 where name='peter'";
            //设置自动提交为false
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql01);
            preparedStatement.executeUpdate();

            preparedStatement1 = connection.prepareStatement(sql02);
            preparedStatement1.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            System.out.println("事务出错，回滚！");
            connection.rollback();
            e.printStackTrace();
        }
        JDBCutils.close(connection,preparedStatement,null);
        JDBCutils.close(null,preparedStatement1,null);
    }
}
