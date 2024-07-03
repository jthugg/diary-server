package com.github.jthugg.diary.util.jwt.model;

public record JwtSet(
        String accessToken,
        String refreshToken
) {}
