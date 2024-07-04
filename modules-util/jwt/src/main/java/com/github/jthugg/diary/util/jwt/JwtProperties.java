package com.github.jthugg.diary.util.jwt;

import com.auth0.jwt.algorithms.Algorithm;
import lombok.Getter;

@Getter
public class JwtProperties {

    private final Algorithm algorithm;
    private final long accessTokenLifeTime;
    private final long refreshTokenLifeTime;

    public JwtProperties(String secret, long accessTokenLifeTime, long refreshTokenLifeTime) {
        this.algorithm = Algorithm.HMAC512(secret);
        this.accessTokenLifeTime = accessTokenLifeTime;
        this.refreshTokenLifeTime = refreshTokenLifeTime;
    }

}
