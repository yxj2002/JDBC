package com.yxj.JDBC.datasource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.jupiter.api.Test;

import javax.management.Query;
import java.sql.Connection;
import java.util.List;

/**
 * @Author:zhili
 * @Time:2022/8/2 16:53
 * @describe:使用apache_DBUtils工具类+druid完成对表的crud
 */
@SuppressWarnings({"all"})
public class DBUtils_USE {

    @Test
    public void testQueryMany() throws Exception {
        Connection connection = JDBCUtilsByDruid.getConnection();
        //创建一个QueryRunner
        QueryRunner queryRunner = new QueryRunner();
        //就可以使用相关方法，返回AarrayList结果集
        String sql="select * from admire where id > ?";
        /**
         * (1)query方法就是执行sql语句，得到resultset---封装到---》ArrayList集合中
         *（2）返回集合
         *（3）connection：连接
         *（4）sql：执行的sql语句
         *（5）new BeanListHandler<>(Admire.class):在将resultset-》Admire对象-》封装到ArrayList底层使用反射机制去获取Admire
         *    类的属性，然后进行封装
         *（6）1就是给sql语句中的？赋值的，可以有多个值，因为是可变形参Object...params
         * (7)底层得到resultset，会在query关闭，关闭PreparedStatement
         *
         */
        List<Admire> query = queryRunner.query(connection, sql, new BeanListHandler<>(Admire.class), 1);
        for(Admire e:query){
            System.out.println(e);
        }
        JDBCUtilsByDruid.close(connection,null,null);
    }

    @Test
    public void testQuerySingle() throws Exception {
        Connection connection = JDBCUtilsByDruid.getConnection();
        //创建一个QueryRunner
        QueryRunner queryRunner = new QueryRunner();
        //就可以使用相关方法，返回AarrayList结果集
        String sql="select * from admire where id = ?";
        //因为我们返回的单行记录《---》单个对象，使用Hander是BeanHandler
        Admire query = queryRunner.query(connection, sql, new BeanHandler<>(Admire.class), 4);
        System.out.println(query);
        JDBCUtilsByDruid.close(connection,null,null);
    }

    //演示apache-dbutils+druid完成查询结果是单行单列-返回的就是object
    @Test
    public void testScalar() throws Exception {
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql="select name from admire where id = ?";
        //因为返回的是单行单列，使用的就是ScalarHandler
        Object object = queryRunner.query(connection,sql,new ScalarHandler(),2);
        System.out.println(object);
        JDBCUtilsByDruid.close(connection,null,null);
    }


    //演示apache-dbutils+druid完成dml
    @Test
    public void testDML() throws Exception {
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql="update admire set name = ? where id = ?";
        /**
         * （1）执行dml的语句是queryRunner.update（）
         *  (2)返回的值是受影响的行数
         */
        int affectedRow = queryRunner.update(connection, sql, "袁修杰", 5);
        System.out.println(affectedRow>0?"执行成功！":"执行没有影响到表！");
        JDBCUtilsByDruid.close(connection,null,null);
    }

}

