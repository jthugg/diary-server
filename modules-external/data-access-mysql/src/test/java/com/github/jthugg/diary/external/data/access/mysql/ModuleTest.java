package com.github.jthugg.diary.external.data.access.mysql;

import com.github.jthugg.diary.external.data.access.mysql.repository.MemberRepository;
import com.github.jthugg.diary.external.data.access.mysql.utils.TestUtils;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ModuleTest {

    static final String TEST_CONTAINER_IMAGE = "mariadb:11.4";
    static final String TEST_CONTAINER_USERNAME = "test";
    static final String TEST_CONTAINER_PASSWORD = "test";
    static final String TEST_CONTAINER_DATABASE = "diary";
    static final String TEST_CONTAINER_INIT_SQL = "sql/schema.sql";
    static final int TEST_CONTAINER_CONNECTION_POOL_SIZE = 20;

    static JdbcDatabaseContainer<?> container;
    static DataSource dataSource;
    static Connection connection;

    @BeforeAll
    static void beforeAll() throws SQLException {
        container = new MariaDBContainer<>(DockerImageName.parse(TEST_CONTAINER_IMAGE))
                .withUsername(TEST_CONTAINER_USERNAME)
                .withPassword(TEST_CONTAINER_PASSWORD)
                .withDatabaseName(TEST_CONTAINER_DATABASE)
                .withInitScript(TEST_CONTAINER_INIT_SQL);
        container.start();
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(container.getDriverClassName());
        hikariConfig.setJdbcUrl(container.getJdbcUrl());
        hikariConfig.setUsername(container.getUsername());
        hikariConfig.setPassword(container.getPassword());
        hikariConfig.setMaximumPoolSize(TEST_CONTAINER_CONNECTION_POOL_SIZE);
        dataSource = new HikariDataSource(hikariConfig);
        connection = dataSource.getConnection();
    }

    @AfterAll
    static void afterAll() throws SQLException {
        connection.close();
        container.stop();
    }

    JdbcTemplate template;
    MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        template = new JdbcTemplate(dataSource);
        memberRepository = new MemberRepository(template);
    }

    @Test
    @Order(0)
    void initialQuery() throws Exception {
        TestUtils.execute(() -> template.queryForObject(
                "SELECT 'Database ready to test!'",
                String.class
        )).log(log);
    }

    @Test
    void createTest() throws Exception {
        TestUtils.execute(() -> memberRepository.create(
                "GITHUB",
                "4566231113"
        )).log(log);
    }

    @Test
    void existsByUsernameTest() throws Exception {
        TestUtils.execute(() -> memberRepository.existsByUsername("user05")).log(log);
    }

    @Test
    void findByIdTest() throws Exception {
        TestUtils.execute(() -> memberRepository.findById(1L).orElse(null)).log(log);
    }

    @Test
    void findByOauthDataTest() throws Exception {
        TestUtils.execute(() -> memberRepository.findByOauthData(
                "GITHUB",
                "321634435"
        )).log(log);
    }

    @Test
    void findByUsernameTest() throws Exception {
        TestUtils.execute(() -> memberRepository.findByUsername("01 02 03 04", 3, 0)).log(log);
    }

}
