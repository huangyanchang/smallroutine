package com.example.smallroutine;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.sql.DataSource;

//测试类中无此注解，将导致service,dao等自动注入失败
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestConnection {

    @Autowired
    DataSource dataSource;

    @Test
    public void mysqlConnection() throws Exception{
            System.out.println("数据库连接为：" + dataSource.getConnection());

     }
}
