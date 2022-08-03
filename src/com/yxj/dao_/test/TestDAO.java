package com.yxj.dao_.test;

import com.yxj.dao_.dao.AdmireDAO;
import com.yxj.dao_.domain.Admire;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @Author:zhili
 * @Time:2022/8/3 0:18
 * @describe:
 */
public class TestDAO {

    @Test
    public void testAdmireDAO(){
        AdmireDAO admireDAO = new AdmireDAO();
        //查询多行结果
        List<Admire> admire = admireDAO.queryMulti("select * from admire where id >= ?", Admire.class, 2);
        for (Admire e:admire){
            System.out.println(e);
        }
        //查询单行结果
        Admire admire1 = admireDAO.querySingle("select * from admire where id = ?", Admire.class, 5);
        System.out.println(admire1);
        //查询单行单列数据
        Object o = admireDAO.queryScalar("select name from admire where id = ?", 2);
        System.out.println(o);
        //修改
        admireDAO.update("update admire set name = '皮特' where id = ?",2);


    }
}
