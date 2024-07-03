package com.github.jthugg.diary.util.jwt;

import com.auth0.jwt.algorithms.Algorithm;

public record JwtProperties(
        Algorithm algorithm,
        long accessTokenLifeTime,
        long refreshTokenLifeTime
) {}
