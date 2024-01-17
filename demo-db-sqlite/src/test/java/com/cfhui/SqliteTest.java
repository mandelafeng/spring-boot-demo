package com.cfhui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * []
 *
 * @author cfhui
 * @version V1
 * @date 2023/12/13 上午 10:12
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SqliteTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test1() {

        // 1、建表 DDL
//        String createUser = "create table user(" +
//            "id integer primary key autoincrement," +
//            "name varchar(20)," +
//            "age integer" +
//            ")";
//        jdbcTemplate.update(createUser);
//        // 2、插入数据
//        String insertUserData = "insert into user(name,age) values ('张三',18),('李四',20)";
//        jdbcTemplate.update(insertUserData);
//        // 3、查询语句
        String selectUserData = "select * from user";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(selectUserData);
        for (Map<String, Object> map : list) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.println(entry.getKey() + "=" + entry.getValue());
            }

        }
    }
}
