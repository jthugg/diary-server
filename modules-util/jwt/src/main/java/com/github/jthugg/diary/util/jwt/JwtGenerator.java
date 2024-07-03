package com.github.jthugg.diary.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.github.jthugg.diary.util.jwt.exception.JwtException;
import com.github.jthugg.diary.util.jwt.exception.JwtExceptions;
import com.github.jthugg.diary.util.jwt.model.JwtSet;
import com.github.jthugg.diary.util.jwt.model.JwtType;

import java.time.Instant;
import java.util.Map;

public class JwtGenerator {

    private static final String TYPE = "typ";

    private final JwtProperties properties;
    private final JwtEncryptor encryptor;

    public JwtGenerator(JwtProperties properties, JwtEncryptor encryptor) {
        this.properties = properties;
        this.encryptor = encryptor;
    }

    public JwtSet generateToken(String subject) throws JwtException {
        try {
            Instant now = Instant.now();
            String accessToken = JWT.create()
                    .withHeader(Map.of(TYPE, JwtType.ACCESS_TOKEN))
                    .withSubject(subject)
                    .withExpiresAt(now.plusSeconds(properties.accessTokenLifeTime()))
                    .sign(properties.algorithm());
            String refreshToken = JWT.create()
                    .withHeader(Map.of(TYPE, JwtType.REFRESH_TOKEN))
                    .withSubject(subject)
                    .withExpiresAt(now.plusSeconds(properties.accessTokenLifeTime()))
                    .sign(properties.algorithm());
            return new JwtSet(encryptor.encrypt(accessToken), encryptor.encrypt(refreshToken));
        } catch (JWTCreationException exception) {
            throw JwtExceptions.JWT_GENERATION_EXCEPTION.getJwtException();
        }
    }

}
