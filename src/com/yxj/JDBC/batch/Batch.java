package com.yxj.JDBC.batch;

import com.yxj.JDBC.utils.JDBCutils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author:zhili
 * @Time:2022/8/1 19:58
 * @describe:演示批处理的使用和与正常方法的速度差别
 */
public class Batch {

    @Test
    public void noBatch() throws SQLException {
        Connection connection = JDBCutils.getConnection();
        String sql = "insert into temp values (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        long l = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            preparedStatement.setInt(1,i);
            preparedStatement.setString(2,"lsia"+i);
            preparedStatement.executeUpdate();
        }
        long j = System.currentTimeMillis();
        System.out.println("耗时："+(j-l));
        JDBCutils.close(connection,preparedStatement,null);
    }

    @Test
    public void batch() throws Exception{
        Connection connection = JDBCutils.getConnection();
        String sql = "insert into temp values (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        long l = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            preparedStatement.setInt(1,i);
            preparedStatement.setString(2,"lsia"+i);
            //将sql语句加入到批处理包中-》看源码
            /**
             *
             *
             */
            preparedStatement.addBatch();
            if((i+1)%1000==0){
                preparedStatement.executeBatch();
                //清空
                preparedStatement.clearBatch();
            }
        }
        long j = System.currentTimeMillis();
        System.out.println("批处理耗时："+(j-l));
        JDBCutils.close(connection,preparedStatement,null);
    }



}
