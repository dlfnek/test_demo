package com.whatap.api.demo.test_demo.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;

// test
@Slf4j
public class SleepSqlExec {

    @Value("spring.datasource.url")
    private String DB_URL;
    @Value("spring.datasource.username")
    private String USERNAME;
    @Value("spring.datasource.password")
    private String PASSWORD;
    @Value("spring.datasource.driver-class-name")
    private String DRIVER_CLASS;

    private Connection getConnection() throws SQLException {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl(DB_URL);
        hikariConfig.setUsername(USERNAME);
        hikariConfig.setPassword(PASSWORD);

        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        Connection conn = null;
        conn = dataSource.getConnection();

        return conn;
    }

    public void insertUserSleep(String username, String password) {
        // SQL example
        String sql = "insert members into values('" + username +"','" + password + "')";

        try {
            Connection conn = this.getConnection();
            // create Statement
            Statement st = conn.createStatement();

            if (st.execute(sql)) {
                log.info("inserted successfully " + sql);
                conn.close();
            } else {
                log.error("Do not inserted! " + sql);
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
