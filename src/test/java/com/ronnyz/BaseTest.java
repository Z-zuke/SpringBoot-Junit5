package com.ronnyz;

import com.alibaba.fastjson.JSON;
import com.ronnyz.dao.entity.User;
import com.ronnyz.mappers.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.event.annotation.AfterTestClass;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

/**
 * The class description.
 *
 * @author Ronnyz
 * @since 2023/9/2
 */
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
public class BaseTest {
    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private UserMapper userMapper;

    @Test
    void test() {
        List<User> userList = userMapper.selectAll();
        Assertions.assertThat(userList).isNotEmpty();
        log.info("userList size : {}", userList.size());

        User user = userMapper.selectByUsername("zhangsan");
        log.info("userInfo: {}", JSON.toJSONString(user));
    }

    @AfterAll
    void tearDown() {
        log.info("close dataSource.");
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        Connection connection = DataSourceUtils.getConnection(dataSource);
        DataSourceUtils.releaseConnection(connection, dataSource);
    }
}
