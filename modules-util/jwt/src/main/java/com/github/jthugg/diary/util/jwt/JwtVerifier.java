package com.github.jthugg.diary.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.github.jthugg.diary.util.jwt.exception.JwtDecryptException;
import com.github.jthugg.diary.util.jwt.exception.JwtExceptions;
import com.github.jthugg.diary.util.jwt.exception.JwtVerifyingException;
import com.github.jthugg.diary.util.jwt.model.ResolvedJwt;

public class JwtVerifier {

    private final JWTVerifier verifier;
    private final JwtEncryptor encryptor;

    public JwtVerifier(JwtProperties properties, JwtEncryptor encryptor) {
        this.verifier = JWT.require(properties.algorithm()).build();
        this.encryptor = encryptor;
    }

    public ResolvedJwt verify(String encryptedToken) throws JwtDecryptException, JwtVerifyingException {
        String tokenValue = encryptor.decrypt(encryptedToken);
        return resolve(tokenValue);
    }

    private ResolvedJwt resolve(String token) {
        try {
            return new ResolvedJwt(verifier.verify(token), false);
        } catch (TokenExpiredException exception) {
            return new ResolvedJwt(JWT.decode(token), true);
        } catch (JWTVerificationException exception) {
            throw JwtExceptions.JWT_VERIFYING_EXCEPTION.getJwtException();
        }
    }

}
