package com.github.jthugg.diary.data.token.storage;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

@Slf4j
@Testcontainers
public class ModuleTest {

    static final String TEST_CONTAINER_IMAGE = "redis:7";
    static final int TEST_CONTAINER_PORT = 6379;

    static GenericContainer<?> container;
    static RedisTemplate<String, String> redisTemplate;

    @BeforeAll
    static void beforeAll() {
        container = new GenericContainer<>(TEST_CONTAINER_IMAGE)
                .withExposedPorts(TEST_CONTAINER_PORT)
                .withReuse(true);
        container.start();

        String host = container.getHost();
        int port = container.getFirstMappedPort();

        LettuceConnectionFactory connFactory = new LettuceConnectionFactory(host, port);
        connFactory.afterPropertiesSet();

        redisTemplate = new RedisTemplate<>();
        redisTemplate.setDefaultSerializer(RedisSerializer.string());
        redisTemplate.setConnectionFactory(connFactory);
        redisTemplate.afterPropertiesSet();
    }

    @AfterAll
    static void afterAll() {
        container.stop();
        container.close();
    }

    @Test
    void luaScriptTest() throws InterruptedException {
        RedisScript<String> script01 = new DefaultRedisScript<>("""
                redis.call('set', 'key01', 'value01')
                redis.call('expire', 'key01', '3')
                return redis.call('get', 'key01')
                """, String.class);
        String value = redisTemplate.execute(script01, List.of());
        log.info("value: {}", value);

        Thread.sleep(3_000L);

        RedisScript<String> script02 = new DefaultRedisScript<>("return redis.call('get', 'key01')", String.class);
        value = redisTemplate.execute(script02, List.of());
        log.info("value: {}", value);
    }

}
