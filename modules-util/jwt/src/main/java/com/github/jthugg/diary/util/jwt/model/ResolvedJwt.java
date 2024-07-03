package com.github.jthugg.diary.util.jwt.model;

import com.auth0.jwt.interfaces.DecodedJWT;

import java.time.Instant;

public class ResolvedJwt {

    private final DecodedJWT decodedJWT;
    private final boolean isExpired;

    public ResolvedJwt(DecodedJWT decodedJWT, boolean isExpired) {
        this.decodedJWT = decodedJWT;
        this.isExpired = isExpired;
    }

    public JwtType getType() {
        return JwtType.valueOf(decodedJWT.getType());
    }

    public String getUserId() {
        return decodedJWT.getSubject();
    }

    public Instant getExpiration() {
        return decodedJWT.getExpiresAtAsInstant();
    }

    public boolean isExpired() {
        return isExpired;
    }

}
