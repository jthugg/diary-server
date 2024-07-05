package com.github.jthugg.diary.gateway.gateway.config;

import com.github.jthugg.diary.data.token.storage.model.Token;
import com.github.jthugg.diary.data.token.storage.repository.TokenRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class TokenStorageAccessConfig {

    @Bean
    public TokenRepository tokenRepository(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Token> tokenRedisTemplate = new RedisTemplate<>();
        tokenRedisTemplate.setConnectionFactory(redisConnectionFactory);
        return new TokenRepository(tokenRedisTemplate);
    }

}
