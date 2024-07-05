package com.github.jthugg.diary.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.github.jthugg.diary.util.jwt.exception.JwtException;
import com.github.jthugg.diary.util.jwt.exception.JwtExceptions;
import com.github.jthugg.diary.util.jwt.model.JwtClaims;
import com.github.jthugg.diary.util.jwt.model.JwtSet;
import com.github.jthugg.diary.util.jwt.model.JwtType;

import java.time.Instant;
import java.util.Map;

public class JwtGenerator {

    private final JwtProperties properties;
    private final JwtEncryptor encryptor;

    public JwtGenerator(JwtProperties properties, JwtEncryptor encryptor) {
        this.properties = properties;
        this.encryptor = encryptor;
    }

    public JwtSet generateToken(String userId, String role) throws JwtException {
        try {
            Instant now = Instant.now();
            String accessToken = createAccessToken(userId, role, now);
            String refreshToken = createRefreshToken(userId, now);
            return new JwtSet(encryptor.encrypt(accessToken), encryptor.encrypt(refreshToken));
        } catch (JWTCreationException exception) {
            throw JwtExceptions.JWT_GENERATION_EXCEPTION.getJwtException();
        }
    }

    private String createAccessToken(String userId, String role, Instant now) throws JwtException {
        return JWT.create()
                .withHeader(Map.of(JwtClaims.Header.TYPE.getClaim(), JwtType.ACCESS_TOKEN))
                .withClaim(JwtClaims.Payload.USER_ID.getClaim(), userId)
                .withClaim(JwtClaims.Payload.USER_ROLE.getClaim(), role)
                .withExpiresAt(now.plusSeconds(properties.getAccessTokenLifeTime()))
                .sign(properties.getAlgorithm());
    }

    private String createRefreshToken(String userId, Instant now) throws JwtException {
        return JWT.create()
                .withHeader(Map.of(JwtClaims.Header.TYPE.getClaim(), JwtType.REFRESH_TOKEN))
                .withClaim(JwtClaims.Payload.USER_ID.getClaim(), userId)
                .withExpiresAt(now.plusSeconds(properties.getRefreshTokenLifeTime()))
                .sign(properties.getAlgorithm());
    }

}
