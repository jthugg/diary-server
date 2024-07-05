package com.github.jthugg.diary.gateway.gateway.config;

import com.github.jthugg.diary.util.jwt.JwtEncryptor;
import com.github.jthugg.diary.util.jwt.JwtProperties;
import com.github.jthugg.diary.util.jwt.JwtVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Bean
    public JwtVerifier jwtVerifier(
            @Value("${jwt.token.secret}") String tokenSecret,
            @Value("${jwt.token.access-token-life-time}") long accessTokenLifeTime,
            @Value("${jwt.token.refresh-token-life-time}") long refreshTokenLifeTime,
            @Value("${jwt.encryption.aesKey}") String aesKey,
            @Value("${jwt.encryption.ivKey}") String ivKey
    ) {
        JwtProperties properties = new JwtProperties(tokenSecret, accessTokenLifeTime, refreshTokenLifeTime);
        JwtEncryptor encryptor = new JwtEncryptor(aesKey, ivKey);
        return new JwtVerifier(properties, encryptor);
    }

}
