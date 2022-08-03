package com.yxj.dao_.dao;

import com.yxj.JDBC.datasource.JDBCUtilsByDruid;
import jdk.nashorn.internal.scripts.JD;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author:zhili
 * @Time:2022/8/2 22:23
 * @describe:开发BasicDAO
 */
@SuppressWarnings({"ALL"})
public class BasicDAO<T> {//泛型指定具体的类型
    private QueryRunner qr=new QueryRunner();

    /**
     * 开发通用的dml
     * @param sql
     * @param parmeters
     * @return 返回受影响的行数
     */
    public int update(String sql,Object... parmeters){
        Connection connection=null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            int update = qr.update(connection, sql, parmeters);
            return update;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.close(connection,null,null);
        }
    }

    /**
     *返回多个对象，针对任意表
     * @param sql sql语句，可以有？
     * @param clazz 传入一个类的Class对象，比如 Admire.class
     * @param parameters  传入？的具体的值，可以是多个
     * @return  根据clazz返回对应的ArrayList集合
     */
    public List<T> queryMulti(String sql,Class<T> clazz,Object... parameters) {
        Connection connection = null;
        List<T> t=null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            t = qr.query(connection,sql,new BeanListHandler<T>(clazz),parameters);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtilsByDruid.close(connection,null,null);
            return t;
        }
    }


    /**
     * 查询单行数据
     * @param sql
     * @param clazz
     * @param paramenters
     * @return
     */
    public T querySingle(String sql,Class<T> clazz,Object... paramenters) {
        Connection connection = null;
        T t=null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            t = qr.query(connection, sql, new BeanHandler<T>(clazz), paramenters);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtilsByDruid.close(connection, null, null);
            return t;
        }
    }


    /**
     * 查询单行单列数据
     * @param sql
     * @param paramenters
     * @return
     */
    public Object queryScalar(String sql,Object... paramenters){
        Connection connection=null;
        Object obj=null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            obj = qr.query(connection,sql,new ScalarHandler<>(),paramenters);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtilsByDruid.close(connection,null,null);
            return obj;
        }
    }



}
