package com.github.jthugg.diary.data.token.storage.repository;

import com.github.jthugg.diary.data.token.storage.exception.TokenNotFoundException;
import com.github.jthugg.diary.data.token.storage.exception.TokenRevokedException;
import com.github.jthugg.diary.data.token.storage.model.Token;
import com.github.jthugg.diary.data.token.storage.model.TokenType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TokenRepository {

    private final RedisTemplate<String, Token> tokenRedisTemplate;
    private final RedisScript<Void> revokeScript;

    public TokenRepository(RedisTemplate<String, Token> tokenRedisTemplate) {
        this.tokenRedisTemplate = tokenRedisTemplate;
        revokeScript = RedisScript.of(new ClassPathResource("/token/storage/script/revoke.lua"), Void.class);
    }

    public Token findAccessToken(String token) {
        Token accessToken = tokenRedisTemplate.opsForValue().get(token);
        if (accessToken == null) {
            throw new TokenNotFoundException();
        }
        if (accessToken.isRevoked()) {
            throw new TokenRevokedException();
        }
        if (!TokenType.ACCESS_TOKEN.equals(accessToken.getType())) {
            throw new TokenNotFoundException();
        }
        return accessToken;
    }

    public Token findRefreshToken(String token) {
        Token refreshToken = tokenRedisTemplate.opsForValue().get(token);
        if (refreshToken == null) {
            throw new TokenNotFoundException();
        }
        if (refreshToken.isRevoked()) {
            throw new TokenRevokedException();
        }
        if (!TokenType.REFRESH_TOKEN.equals(refreshToken.getType())) {
            throw new TokenNotFoundException();
        }
        return refreshToken;
    }

    public Token setAccessToken(String token, long ttl) {
        Token accessToken = Token.newAccessToken(token);
        tokenRedisTemplate.opsForValue().set(token, accessToken, ttl, TimeUnit.SECONDS);
        return accessToken;
    }

    public Token setRefreshToken(String token, long ttl) {
        Token refreshToken = Token.newRefreshToken(token);
        tokenRedisTemplate.opsForValue().set(token, refreshToken, ttl, TimeUnit.SECONDS);
        return refreshToken;
    }

    public void revokeToken(String token) {
        tokenRedisTemplate.execute(revokeScript, List.of(token));
    }

}
