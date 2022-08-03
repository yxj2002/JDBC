package com.yxj.JDBC;

import com.mysql.cj.jdbc.Driver;
import com.sun.xml.internal.ws.developer.UsesJAXBContext;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author:zhili
 * @Time:2022/7/31 19:33
 * @describe:java连接MySQL的5种方式
 */
public class JDBC02 {
    public void connect01(){

    }

    @Test
    public void connect02() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        //使用反射加载Drive类
        Class<?> cls = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver)cls.newInstance();
        String url="jdbc:mysql://localhost:3306/yxj_de03";
        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","70118084");
        Connection connect = driver.connect(url, properties);
        System.out.println("方式二："+connect);
    }

    @Test
    public void connect03() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        //使用DriverManager代替Driver进行管理
        Class<?> cls = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver)cls.newInstance();
        String url="jdbc:mysql://localhost:3306/yxj_de03";
        String user="root";
        String password="70118084";

        DriverManager.registerDriver(driver);//注册Driver驱动
        Connection connection = DriverManager.getConnection(url, user,password);
        System.out.println("方式三："+connection);
    }


    @Test
    public void connect04() throws ClassNotFoundException, SQLException {
        //使用Class.forName自动完成注册驱动，简化代码
        //加载Driver类是自动完成注册
        /**
         * DriverManager.registerDriver(new Driver())将注册完成
         *
         *  static {
         *         try {
         *             DriverManager.registerDriver(new Driver());
         *         } catch (SQLException var1) {
         *             throw new RuntimeException("Can't register driver!");
         *         }
         *     }
         *
         */
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/yxj_de03";
        String user="root";
        String password="70118084";

        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("方式四："+connection);

    }

    @Test
    public void connect05() throws IOException, ClassNotFoundException, SQLException {
        //使用配置文件，连接数据库更灵活
        //通过Properties对象获取配置文件的信息
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.Properties"));
        String user=properties.getProperty("user");
        String password=properties.getProperty("password");
        String url=properties.getProperty("url");
        String driver=properties.getProperty("driver");
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("方式五："+connection);


    }


}
